package com.uade.tpo.ecommerceback.service.implementations;

import com.uade.tpo.ecommerceback.controllers.auth.*;
import com.uade.tpo.ecommerceback.controllers.configuration.JwtService;
import com.uade.tpo.ecommerceback.entity.Rol;
import com.uade.tpo.ecommerceback.entity.Usuario;
import com.uade.tpo.ecommerceback.exceptions.NotFoundException;
import com.uade.tpo.ecommerceback.repository.UserRepository;
import com.uade.tpo.ecommerceback.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMail(),
                        request.getContrasenia()));

        var user = repository.findByMail(request.getMail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse register(UserAttributesRequestDto request) {
        if (!isPasswordValid(request.getContrasenia())) {
            throw new IllegalArgumentException("La contraseña no cumple con al menos una de las siguientes condiciones: 8 caracteres, 1 minúscula, 1 mayúscula y un número");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .documento(request.getDocumento())
                .mail(request.getMail())
                .contrasenia(passwordEncoder.encode(request.getContrasenia()))
                .rol(Rol.ROLE_CLIENTE)
                .expiredAccount(true)
                .lockedAccount(true)
                .expiredCredentials(true)
                .enabled(true)
                .build();

        repository.save(usuario);

        return AuthenticationResponse.builder()
                .accessToken(jwtService.generateToken((UserDetails) usuario))
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(),request.getContrasenia()));
        UserDetails usuario = (UserDetails) repository.findByMail(request.getMail()).orElseThrow();
        String token = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public MessageDto changeAccountData(UserNewPasswordDto request) {
        authenticate(new AuthenticationRequestDto(request.getMail(), request.getActualContrasenia()));
        Usuario usuarioDestino = repository.findByMail(request.getMail()).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        if (!isPasswordValid(request.getNuevaContrasenia())) {
            throw new IllegalArgumentException("La contraseña no cumple con al menos una de las siguientes condiciones: 8 caracteres, 1 minúscula, 1 mayúscula y un número");
        }
        usuarioDestino.setContrasenia(passwordEncoder.encode(request.getNuevaContrasenia()));
        repository.save(usuarioDestino);
        return new MessageDto("Datos del usuario actualizados correctamente.");
    }

    private boolean isPasswordValid(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
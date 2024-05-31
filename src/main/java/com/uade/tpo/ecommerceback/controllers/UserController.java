package com.uade.tpo.ecommerceback.controllers;

import com.uade.tpo.ecommerceback.Dto.AuthenticationRequestDto;
import com.uade.tpo.ecommerceback.Dto.UserAttributesRequestDto;
import com.uade.tpo.ecommerceback.Dto.UserNewPasswordDto;
import com.uade.tpo.ecommerceback.service.IAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class UserController {

    private final IAuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody UserAttributesRequestDto request){
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PutMapping("/user/change")
    public ResponseEntity<?> changeAccountPassword(@RequestBody UserNewPasswordDto request){
        return ResponseEntity.ok(authService.changeAccountData(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto request){
        return ResponseEntity.ok(authService.login(request));
    }
}

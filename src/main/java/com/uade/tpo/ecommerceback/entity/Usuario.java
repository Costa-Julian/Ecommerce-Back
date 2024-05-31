package com.uade.tpo.ecommerceback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario implements UserDetails {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private Integer documento;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String contrasenia;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compra;

    @Enumerated(EnumType.STRING)
    Rol rol;
    @Column(columnDefinition = "boolean default true")
    boolean expiredAccount;
    @Column(columnDefinition = "boolean default true")
    boolean lockedAccount;
    @Column(columnDefinition = "boolean default true")
    boolean expiredCredentials;
    @Column(columnDefinition = "boolean default true")
    boolean enabled;

    public void changeAccountStatus(boolean finalStatus){
        setEnabled(finalStatus);
    }
    public void changeExpiredAccount(boolean finalStatus){
        setExpiredAccount(finalStatus);
    }
    public void changeAccountLocked(boolean finalStatus){
        setExpiredAccount(finalStatus);
    }
    public void changeExpiredCredentials(boolean finalStatus){
        setExpiredAccount(finalStatus);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expiredAccount;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lockedAccount;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return expiredCredentials;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

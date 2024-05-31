package com.uade.tpo.ecommerceback.controllers.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNewPasswordDto {
    private String mail;
    private String actualContrasenia;
    private String nuevaContrasenia;
}

package com.uade.tpo.ecommerceback.Dto;


import com.uade.tpo.ecommerceback.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAttributesRequestDto {
    private String nombre;
    private String apellido;
    private Integer documento;
    private String mail;
    private String contrasenia;
    private Rol rol;
}

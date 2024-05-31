package com.uade.tpo.ecommerceback.Dto;

import lombok.Data;

@Data
public class ProductoRequestDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private long stock;
    private Long idCategoria;
}


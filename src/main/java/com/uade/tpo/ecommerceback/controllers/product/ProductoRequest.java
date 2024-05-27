package com.uade.tpo.ecommerceback.controllers.product;

import lombok.Data;

@Data
public class ProductoRequest {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long idCategoria;
}


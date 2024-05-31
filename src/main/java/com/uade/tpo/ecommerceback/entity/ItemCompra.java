package com.uade.tpo.ecommerceback.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemCompra {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @Column(nullable = false)
    private double precioUnitario;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

}

package com.uade.tpo.ecommerceback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity

@Data

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private double monto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "descuentos")
    private Descuento descuentos;

    @ManyToOne
    @JoinColumn(name = "items_compra")
    private ItemCompra items_compra;

    @OneToMany(mappedBy = "compra")
    private List<ItemCompra> items;
}
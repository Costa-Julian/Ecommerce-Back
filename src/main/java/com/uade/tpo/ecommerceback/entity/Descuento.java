package com.uade.tpo.ecommerceback.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity

@Data

public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private double porcentaje;

    @Column(nullable = false)
    private boolean habilitado;

    @OneToMany(mappedBy = "descuentos")
    @JsonIgnore
    private List<Compra> descuentos;

    public Descuento(String descripcion, double porcentaje, boolean habilitado) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
        this.habilitado = habilitado;
    }
    public Descuento() {}

}

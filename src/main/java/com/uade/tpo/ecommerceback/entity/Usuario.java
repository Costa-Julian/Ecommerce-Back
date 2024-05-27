package com.uade.tpo.ecommerceback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity

@Data

public class Usuario {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @OneToOne(mappedBy = "usuario")
    private Compra compra;

    @OneToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private TipoUsuario rol;
}
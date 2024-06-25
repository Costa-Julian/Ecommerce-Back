package com.uade.tpo.ecommerceback.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "problems1")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre y Apellido es obligatorio")
    private String fullName;

    @NotBlank(message = "Problemática es obligatoria")
    private String issue;

    @NotBlank(message = "Descripción del problema es obligatoria")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private List<Image> images;
}

package com.uade.tpo.ecommerceback.controllers;


import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
public class ProblemRequest {

    @NotEmpty(message = "El nombre y apellido es obligatorio")
    @Size(max = 100, message = "El nombre y apellido no puede exceder los 100 caracteres")
    private String fullName;

    @NotEmpty(message = "La problemática es obligatoria")
    private String issue;

    @NotEmpty(message = "La descripción del problema es obligatoria")
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    private List<MultipartFile> files;
}

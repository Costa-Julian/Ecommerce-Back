package com.uade.tpo.ecommerceback.controllers;


import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ProblemResponse {
    private Long id;
    private String fullName;
    private String issue;
    private String description;
    private List<ImageResponse> images;
}

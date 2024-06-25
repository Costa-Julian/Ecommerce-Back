package com.uade.tpo.ecommerceback.controllers;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class AddFileRequest {
    private String name;
    private List<MultipartFile> files = new ArrayList<>();
}
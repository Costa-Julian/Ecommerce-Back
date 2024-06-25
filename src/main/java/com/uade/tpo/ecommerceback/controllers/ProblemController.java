package com.uade.tpo.ecommerceback.controllers;


import com.uade.tpo.ecommerceback.entity.Image;
import com.uade.tpo.ecommerceback.entity.Problem;
import com.uade.tpo.ecommerceback.service.ImageService;
import com.uade.tpo.ecommerceback.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ImageService imageService;

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<String> createProblem(@Valid @ModelAttribute ProblemRequest request,
                                                BindingResult bindingResult) throws IOException, SQLException {
        if (request.getFiles() == null || request.getFiles().isEmpty()) {
            bindingResult.rejectValue("files", "error.files", "Debe proporcionar al menos una imagen");
        }

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        Problem problem = Problem.builder()
                .fullName(request.getFullName())
                .issue(request.getIssue())
                .description(request.getDescription())
                .build();
        problemService.save(problem);

        if (request.getFiles() != null) {
            for (MultipartFile file : request.getFiles()) {
                if (!file.isEmpty()) {
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    Image image = Image.builder()
                            .problemId(problem.getId())
                            .image(blob)
                            .build();
                    imageService.create(image);
                }
            }
        }

        return ResponseEntity.ok("Problem created successfully with ID: " + problem.getId());
    }

    @CrossOrigin
    @GetMapping("/{problemId}")
    public ProblemResponse getProblemById(@PathVariable("problemId") Long problemId) throws SQLException {
        Problem problem = problemService.getById(problemId);

        if (problem != null) {
            List<Image> images = imageService.getImagesByProblemId(problemId);

            List<ImageResponse> imageResponses = images.stream()
                    .map(image -> ImageResponse.builder()
                            .id(image.getId())
                            .file(convertBlobToBase64(image.getImage()))
                            .build())
                    .collect(Collectors.toList());

            return ProblemResponse.builder()
                    .id(problem.getId())
                    .fullName(problem.getFullName())
                    .issue(problem.getIssue())
                    .description(problem.getDescription())
                    .images(imageResponses)
                    .build();
        } else {
            return null;
        }
    }

    private String convertBlobToBase64(Blob blob) {
        try {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
package com.uade.tpo.ecommerceback.controllers;

import com.uade.tpo.ecommerceback.Dto.CategoriaRequestDto;
import com.uade.tpo.ecommerceback.entity.Categoria;
import com.uade.tpo.ecommerceback.exceptions.CategoryDuplicateException;
import com.uade.tpo.ecommerceback.service.ICategoriaService;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<Categoria>> getCategories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(categoriaService.getCategories(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(categoriaService.getCategories(PageRequest.of(page, size)));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Categoria> getCategoryById(@PathVariable Long categoryId) {
        Optional<Categoria> result = categoriaService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCategory(@RequestBody CategoriaRequestDto CategoriaRequestDto)
            throws CategoryDuplicateException {
        Categoria result = categoriaService.createCategory(CategoriaRequestDto.getNombre());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }

}

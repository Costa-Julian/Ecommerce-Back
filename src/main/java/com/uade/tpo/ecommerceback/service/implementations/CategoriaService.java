package com.uade.tpo.ecommerceback.service.implementations;

import com.uade.tpo.ecommerceback.entity.Categoria;
import com.uade.tpo.ecommerceback.exceptions.CategoryDuplicateException;
import com.uade.tpo.ecommerceback.repository.ICategoriaRepository;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.ecommerceback.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private ICategoriaRepository ICategoriaRepository;

    public Page<Categoria> getCategories(PageRequest pageable) {
        return ICategoriaRepository.findAll(pageable);
    }

    public Optional<Categoria> getCategoryById(Long categoryId) {
        return ICategoriaRepository.findById(categoryId);
    }

    public Categoria createCategory(String nombre) throws CategoryDuplicateException {
        List<Categoria> categorias = ICategoriaRepository.findByNombre(nombre);
        if (categorias.isEmpty())
            return ICategoriaRepository.save(new Categoria(nombre));
        throw new CategoryDuplicateException();
    }
}

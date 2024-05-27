package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.entity.Categoria;
import com.uade.tpo.ecommerceback.exceptions.CategoryDuplicateException;

public interface ICategoryService {
    public Categoria createCategory(String description) throws CategoryDuplicateException;
}

package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.exceptions.ProductoDuplicateExeption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    public Page<Producto> findAll(PageRequest pr);
    public Optional<Producto> findById(long id);
    public Producto createProducto(Producto producto) throws ProductoDuplicateExeption;
}

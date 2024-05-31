package com.uade.tpo.ecommerceback.service.implementations;

import com.uade.tpo.ecommerceback.entity.Categoria;
import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.repository.ICategoriaRepository;
import com.uade.tpo.ecommerceback.repository.IProductoRepository;
import com.uade.tpo.ecommerceback.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    public Page<Producto> findAll(PageRequest pr) {
        return productoRepository.findAll(pr);
    }

    @Override
    public Optional<Producto> findById(long id) {
        return productoRepository.findById(id);
    }


    public Producto createProducto(Producto producto) {
        Categoria categoria = producto.getCategoria();
        if (categoria != null && categoria.getId() == null) {
            categoria = categoriaRepository.save(categoria);
        }

        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

}

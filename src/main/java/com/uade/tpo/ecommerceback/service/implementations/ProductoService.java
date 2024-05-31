package com.uade.tpo.ecommerceback.service.implementations;

import com.uade.tpo.ecommerceback.entity.Categoria;
import com.uade.tpo.ecommerceback.entity.Descuento;
import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.exceptions.CategoryDuplicateException;
import com.uade.tpo.ecommerceback.exceptions.ProductoDuplicateExeption;
import com.uade.tpo.ecommerceback.repository.ICategoriaRepository;
import com.uade.tpo.ecommerceback.repository.IDescuentoRepository;
import com.uade.tpo.ecommerceback.repository.IProductoRepository;
import com.uade.tpo.ecommerceback.service.ICategoriaService;
import com.uade.tpo.ecommerceback.service.IDescuentoService;
import com.uade.tpo.ecommerceback.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ICategoriaService categoriaService;
    @Autowired
    private  ICategoriaRepository iCategoriaRepository;
    @Autowired
    private IDescuentoService iDescuentoService;

    @Override
    public Page<Producto> findAll(PageRequest pr) {
        return productoRepository.findAll(pr);
    }

    @Override
    public Optional<Producto> findById(long id) {
        return productoRepository.findById(id);
    }


    public Producto createProducto(Producto producto) {
        Categoria categoria = categoriaService.getCategoryById(producto.getCategoria().getId()).orElse(null);
        if (categoria == null) {
            Categoria c = new Categoria(producto.getCategoria().getNombre());
            categoria = iCategoriaRepository.save(c);
        }
        producto.setCategoria(categoria);
        Producto productoExistente = productoRepository.findFirstByNombre(producto.getNombre());
        if (productoExistente == null) {
            return productoRepository.save(producto);
        }
            productoExistente.setStock(productoExistente.getStock() + 1);
        return updateProducto(productoExistente);
    }
    @Override
    public Producto updateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> findByCategoriaId(long idCategoria) {
        return productoRepository.findAllByCategories(idCategoria);
    }

}

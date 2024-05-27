package com.uade.tpo.ecommerceback.controllers.product;

import com.uade.tpo.ecommerceback.entity.Categoria;
import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.exceptions.ProductoDuplicateExeption;
import com.uade.tpo.ecommerceback.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Producto")
public class ProductController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/All")
    public ResponseEntity<Page<Producto>> getAllProductos(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productoService.findAll(PageRequest.of(0,Integer.MAX_VALUE)));
        return ResponseEntity.ok(productoService.findAll(PageRequest.of(page, size)));
    }

    @PostMapping("/Create")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto createdProducto = productoService.createProducto(producto);
        return new ResponseEntity<>(createdProducto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id) ;
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

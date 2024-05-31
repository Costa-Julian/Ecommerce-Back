package com.uade.tpo.ecommerceback.controllers.product;

import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @GetMapping("/Categoria/{idCategoria}")
    public ResponseEntity<Page<Producto>> getAllProductByCategoria(
            @PathVariable Long idCategoria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Producto> productos = productoService.findByCategoriaId(idCategoria);
        Pageable pageable = PageRequest.of(page, size);
        int start = Math.min((int) pageable.getOffset(), productos.size());
        int end = Math.min((start + pageable.getPageSize()), productos.size());
        Page<Producto> pageProductos = new PageImpl<>(productos.subList(start, end), pageable, productos.size());
        return ResponseEntity.ok(pageProductos);
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

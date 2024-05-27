package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.service.ProductoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value="select p from Producto p where p.nombre = ?1")
    List<Producto> findByNombre(String nombre);
}

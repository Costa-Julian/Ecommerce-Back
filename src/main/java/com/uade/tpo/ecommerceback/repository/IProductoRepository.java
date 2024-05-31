package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) = LOWER(?1)")
    Producto findFirstByNombre(String nombre);
    @Query("SELECT p FROM Producto p WHERE p.categoria = ?1")
    List<Producto> findAllByCategories(Long categoria);
}

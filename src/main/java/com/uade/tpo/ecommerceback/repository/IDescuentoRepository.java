package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDescuentoRepository  extends JpaRepository<Descuento, Long> {
    @Query("SELECT p FROM Descuento p WHERE LOWER(p.descripcion) = LOWER(?1)")
    Descuento findFirstByDescripcion(String descripcion);
}

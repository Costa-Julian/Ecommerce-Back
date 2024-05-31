package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "select c from Categoria c where LOWER(c.nombre) = LOWER(?1)")
    List<Categoria> findByNombre(String nombre);
}

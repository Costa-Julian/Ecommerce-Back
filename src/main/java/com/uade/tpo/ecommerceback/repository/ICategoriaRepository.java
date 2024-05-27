package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}

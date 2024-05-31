package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDescuentoRepository  extends JpaRepository<Descuento, Long> {
}

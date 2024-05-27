package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByMail(String email);
}

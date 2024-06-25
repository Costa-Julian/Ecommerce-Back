package com.uade.tpo.ecommerceback.repository;


import com.uade.tpo.ecommerceback.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProblemId(Long problemId);
}
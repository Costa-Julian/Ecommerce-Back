package com.uade.tpo.ecommerceback.repository;


import com.uade.tpo.ecommerceback.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
}

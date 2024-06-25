package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.entity.Problem;
import org.springframework.stereotype.Service;

@Service
public interface ProblemService {
    Problem save(Problem problem);

    Problem getById(Long id);
}
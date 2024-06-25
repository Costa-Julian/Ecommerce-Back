package com.uade.tpo.ecommerceback.service;


import com.uade.tpo.ecommerceback.entity.Problem;
import com.uade.tpo.ecommerceback.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Problem save(Problem problem) {
        return problemRepository.save(problem);
    }

    @Override
    public Problem getById(Long id) {
        return problemRepository.findById(id).orElse(null);
    }
}

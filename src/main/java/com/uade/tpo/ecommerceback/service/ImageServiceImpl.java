package com.uade.tpo.ecommerceback.service;


import com.uade.tpo.ecommerceback.entity.Image;
import com.uade.tpo.ecommerceback.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image viewById(long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Image> getImagesByProblemId(Long problemId) {
        return imageRepository.findByProblemId(problemId);
    }
}

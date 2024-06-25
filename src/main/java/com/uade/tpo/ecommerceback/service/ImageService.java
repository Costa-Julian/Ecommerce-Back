package com.uade.tpo.ecommerceback.service;


import com.uade.tpo.ecommerceback.entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {
    public Image create(Image image);

    public Image viewById(long id);

    public List<Image> getImagesByProblemId(Long problemId);

}

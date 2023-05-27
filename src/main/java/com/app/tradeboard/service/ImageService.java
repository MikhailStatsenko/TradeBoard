package com.app.tradeboard.service;

import com.app.tradeboard.model.Image;
import com.app.tradeboard.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public Image getImageById(long id) {
        return imageRepository.findById(id).orElse(null);
    }
}

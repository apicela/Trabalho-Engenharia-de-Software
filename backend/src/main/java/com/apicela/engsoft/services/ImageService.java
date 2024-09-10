package com.apicela.engsoft.services;

import com.apicela.engsoft.utils.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ImageService {

    public Image convertMultipartFileToImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setImageName(file.getOriginalFilename());
        image.setImageType(file.getContentType());
        image.setImageData(file.getBytes());
        return image;
    }
    private Set<Image> convertMultipartFilesToImages(MultipartFile[] files) throws IOException {
        Set<Image> images = new HashSet<>();
        if (files != null) {
            for (MultipartFile file : files) {
                Image image = new Image();
                image.setImageName(file.getOriginalFilename());
                image.setImageType(file.getContentType());
                image.setImageData(file.getBytes());
                images.add(image);
            }
        }
        return images;
    }
}

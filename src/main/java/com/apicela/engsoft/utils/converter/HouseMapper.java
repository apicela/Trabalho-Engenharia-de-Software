package com.apicela.engsoft.utils.converter;

import com.apicela.engsoft.dtos.HouseDTO;
import com.apicela.engsoft.models.residencial.House;
import com.apicela.engsoft.services.ImageService;
import com.apicela.engsoft.utils.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Log4j2
public class HouseMapper {
    final ModelMapper mapper = new ModelMapper();
    final ImageService imageService;

    public House mapDtoToEntity(HouseDTO houseDTO) throws IOException {
        House house = mapper.map(houseDTO, House.class);
        log.info("houseDTO images: {}", Arrays.toString(houseDTO.getImages()));
        // Lógica para converter MultipartFile[] para Set<Image>
        if (houseDTO.getImages() != null) {
            Set<Image> images = new HashSet<>();
            for (MultipartFile file : houseDTO.getImages()) {
                Image image = imageService.convertMultipartFileToImage(file);
                image.setProperty(house);
                images.add(image);
            }
            house.setImages(images);
        } else {
            Set<Image> updatedImages = new HashSet<>();  // Supondo que houseDTO tenha um método getImages()
            house.setImages(updatedImages);
        }

        return house;
    }

    public HouseDTO mapEntityToDto(House house) {
        return mapper.map(house, HouseDTO.class);
    }

}

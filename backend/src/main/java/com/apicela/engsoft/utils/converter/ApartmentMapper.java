package com.apicela.engsoft.utils.converter;

import com.apicela.engsoft.dtos.ApartmentDTO;
import com.apicela.engsoft.models.residencial.Apartment;
import com.apicela.engsoft.services.ImageService;
import com.apicela.engsoft.utils.Image;
import lombok.RequiredArgsConstructor;
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
public class ApartmentMapper {
    final ModelMapper mapper = new ModelMapper();
    final ImageService imageService;

    public Apartment mapDtoToEntity(ApartmentDTO apartmentDTO) throws IOException {
        Apartment house = mapper.map(apartmentDTO, Apartment.class);
        log.info("apartmentDTO images: {}", Arrays.toString(apartmentDTO.getImages()));
        // Lógica para converter MultipartFile[] para Set<Image>
        Set<Image> images = new HashSet<>();// Supondo que apartmentDTO tenha um método getImages()
        if(apartmentDTO.getImagesSet() != null){
            apartmentDTO.getImagesSet().forEach( it ->{
                        it.setId(null);
                        it.setProperty(house);
                    });

            images = apartmentDTO.getImagesSet();
        } else if (apartmentDTO.getImages() != null) {
            for (MultipartFile file : apartmentDTO.getImages()) {
                Image image = imageService.convertMultipartFileToImage(file);
                image.setProperty(house);
                images.add(image);
            }
        }
        house.setImages(images);

        return house;
    }

    public ApartmentDTO mapEntityToDto(Apartment apartment) {
        return mapper.map(apartment, ApartmentDTO.class);
    }

}
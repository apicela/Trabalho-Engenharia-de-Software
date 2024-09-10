package com.apicela.engsoft.dtos;

import com.apicela.engsoft.models.PropertyType;
import com.apicela.engsoft.utils.Address;
import com.apicela.engsoft.utils.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data // getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentDTO {
    private PropertyType propertyType;
    private boolean isRented;
    private Address address;
    private float rentValue;
    private String description;
    private float area;
    private int rooms;
    private int suits;
    private int livingRoom;
    private int vacanciesGarage;
    private boolean closets;
    private int floor;
    private int diningRoom;
    private float condominiumValue;
    private boolean concierge24h;
    @JsonIgnore
    private MultipartFile[] images;
    @JsonIgnore
    private Set<Image> imagesSet;
}

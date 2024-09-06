package com.apicela.engsoft.dtos;

import com.apicela.engsoft.utils.Address;
import com.apicela.engsoft.utils.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data // getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class HouseDTO {
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
    private MultipartFile[] images;
}

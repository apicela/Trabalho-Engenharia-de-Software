package com.apicela.engsoft.dtos;

import com.apicela.engsoft.utils.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentDTO {
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
}

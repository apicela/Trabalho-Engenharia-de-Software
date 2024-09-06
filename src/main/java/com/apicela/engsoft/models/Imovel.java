package com.apicela.engsoft.models;

import com.apicela.engsoft.utils.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Imovel {
    private boolean isRented = false;
    private Address address;
    private float  rentValue;
    private String description;
    private float area;
    private int rooms;
    private int suits;
    private int livingRoom;
    private int vacanciesGarage;
    private boolean closets;
}

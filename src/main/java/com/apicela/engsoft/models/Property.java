package com.apicela.engsoft.models;

import com.apicela.engsoft.utils.Address;
import com.apicela.engsoft.utils.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Property {
    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    private Address address;

    private boolean isRented = false;
    private float  rentValue;
    private String description;
    private float area;
    private int rooms;
    private int suits;
    private int livingRoom;
    private int vacanciesGarage;
    private boolean closets;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images;
}

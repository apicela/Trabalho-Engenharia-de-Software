package com.apicela.engsoft.models;

import com.apicela.engsoft.models.residencial.Apartment;
import com.apicela.engsoft.utils.Address;
import com.apicela.engsoft.utils.Image;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Calendar;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Property {
    @Id
    @GeneratedValue
    private UUID id;

    private PropertyType propertyType;

    @Embedded
    private Address address;

    private boolean isRented;
    private float rentValue;
    private String description;
    private float area;
    private int rooms;
    private int suits;
    private int livingRoom;
    private int vacanciesGarage;
    private boolean closets;

    @JsonManagedReference
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.EAGER)
    private Set<Image> images;
}

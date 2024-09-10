package com.apicela.engsoft.utils;

import com.apicela.engsoft.models.Property;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    private String imageName;
    private String imageType;

    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] imageData;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
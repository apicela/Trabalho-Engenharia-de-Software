package com.apicela.engsoft.utils;

import com.apicela.engsoft.models.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    private String imageName;
    private String imageType;

    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
package com.apicela.engsoft.models.residencial;

import com.apicela.engsoft.models.Property;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Apartment extends Property {
    private int floor;
    private int diningRoom;
    private float condominiumValue;
    private boolean concierge24h;
}

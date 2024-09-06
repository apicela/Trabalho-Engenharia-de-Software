package com.apicela.engsoft.models.residencial;

import com.apicela.engsoft.models.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Apartment extends Property {
    private int floor;
    private int diningRoom;
    private float condominiumValue;
    private boolean concierge24h;

}

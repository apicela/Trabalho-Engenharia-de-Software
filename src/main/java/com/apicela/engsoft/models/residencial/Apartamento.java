package com.apicela.engsoft.models.residencial;

import com.apicela.engsoft.models.Imovel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Apartamento extends Imovel {
    @Id
    @GeneratedValue
    private UUID id;
    private int floor;
    private int diningRoom;
    private float condominiumValue;
    private boolean concierge24h;
}

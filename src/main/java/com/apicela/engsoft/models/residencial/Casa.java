package com.apicela.engsoft.models.residencial;

import com.apicela.engsoft.models.Imovel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Casa extends Imovel {
    @Id
    @GeneratedValue
    private UUID id;
}

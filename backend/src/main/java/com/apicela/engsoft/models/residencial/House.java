package com.apicela.engsoft.models.residencial;

import com.apicela.engsoft.models.Property;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
public class House extends Property {

}

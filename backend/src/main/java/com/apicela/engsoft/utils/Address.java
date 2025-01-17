package com.apicela.engsoft.utils;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String region;
    private String cep;
    private String complement;
    private int number;

}

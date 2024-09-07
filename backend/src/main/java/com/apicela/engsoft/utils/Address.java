package com.apicela.engsoft.utils;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String region;
    private String cep;
    private String complement;
}
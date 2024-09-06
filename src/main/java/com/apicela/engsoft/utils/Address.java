package com.apicela.engsoft.utils;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address  {
    private String street;
}

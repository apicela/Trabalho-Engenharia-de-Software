package com.apicela.engsoft.service;

import com.apicela.engsoft.models.PropertyType;
import com.apicela.engsoft.models.residencial.Apartment;
import com.apicela.engsoft.repositories.ApartmentRepository;
import com.apicela.engsoft.services.PropertyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertyServiceTest {

    @Autowired
    PropertyService propertyService;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Test
    @Transactional
     void obterPropriedade(){
        Apartment apartment = Apartment.builder()
                .description( " Penthouse")
                .rentValue(400f)
                .address(null)
                .propertyType(PropertyType.APARTAMENTO)
                .floor(3)
                .build();
        apartmentRepository.save(apartment);
        var item = propertyService.getById(apartment.getId());
        System.out.println("item: " + item);
    }

}

package com.apicela.engsoft.services;

import com.apicela.engsoft.models.Property;
import com.apicela.engsoft.models.residencial.House;
import com.apicela.engsoft.repositories.ApartmentRepository;
import com.apicela.engsoft.repositories.HouseRepository;
import com.apicela.engsoft.repositories.PropertyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAllProperties() {
        List<Property> properties= propertyRepository.findAll();
        log.info("properties size:{}", properties.size());
        return properties;
    }
}

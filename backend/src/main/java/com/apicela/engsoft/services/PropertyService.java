package com.apicela.engsoft.services;

import com.apicela.engsoft.models.Property;
import com.apicela.engsoft.repositories.PropertyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        log.info("properties size:{}", properties.size());
        return properties;
    }

    public Property getById(UUID id){
        var object = propertyRepository.getById(id);
        log.info("obj to return: {}", object);
        return object;
    }
}

package com.apicela.engsoft.controllers;

import com.apicela.engsoft.dtos.HouseDTO;
import com.apicela.engsoft.models.Property;
import com.apicela.engsoft.services.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController()
@Tag(name = "1. Property ", description = "CRUD")
@RequestMapping("/properties")
@Log4j2
@CrossOrigin("*")
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping
    @Operation(summary = "Obter todas propriedades", description = "Here, you can get a list of objects")
    public ResponseEntity<List<Property>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter Por ID", description = "")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id) throws IOException {
        var property = propertyService.getById(id);
        if (property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado objeto para o ID");
        } else {
            Hibernate.initialize(property.getImages());
            return ResponseEntity.status(HttpStatus.OK).body(property);
        }
    }
}

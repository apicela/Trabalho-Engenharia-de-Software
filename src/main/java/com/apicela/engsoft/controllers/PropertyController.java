package com.apicela.engsoft.controllers;

import com.apicela.engsoft.models.Property;
import com.apicela.engsoft.services.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@Tag(name = "1. Property ", description = "CRUD")
@RequestMapping("/property")
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
}

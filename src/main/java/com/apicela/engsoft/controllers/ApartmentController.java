package com.apicela.engsoft.controllers;

import com.apicela.engsoft.models.Property;
import com.apicela.engsoft.models.residencial.Apartment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@Tag(name = "3. Apartment ", description = "CRUD")
@RequestMapping("/apartment")
@Log4j2
@CrossOrigin("*")
public class ApartmentController {
    @GetMapping
    @Operation(summary = "Obter todos apartamentos", description = "Here, you can get a list of objects")
    public ResponseEntity<List<Apartment>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }

}

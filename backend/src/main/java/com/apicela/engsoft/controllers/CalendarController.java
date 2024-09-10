package com.apicela.engsoft.controllers;

import com.apicela.engsoft.services.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController()
@Tag(name = "4.Calendar ", description = "CRUD")
@RequestMapping("/calendar")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin("*")
public class CalendarController {
    final CalendarService calendarService;

    @GetMapping()
    @Operation(summary = "Obter todos", description = "")
    public ResponseEntity<Object> getAll() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter Por ID", description = "")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id) throws IOException {
        var calendar = calendarService.findById(id);
        if (calendar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado objeto para o ID");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(calendar);
        }
    }

    @GetMapping("propertyId/{propertyId}")
    @Operation(summary = "Obter Por ID", description = "")
    public ResponseEntity<Object> getByPropertyId(@PathVariable(value = "propertyId") UUID id) throws IOException {
        var calendar = calendarService.findByPropertyId(id);
        if (calendar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado objeto para o ID");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(calendar);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar Calendario", description = "")
    public ResponseEntity<Object> updateCalendar(@PathVariable(value = "id") UUID id,
                                                 @RequestBody String startTime) throws IOException {
        log.info("{}", startTime);
        var calendar = calendarService.update(id, startTime);
        if (calendar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado objeto para o ID");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(calendar);
        }
    }

}

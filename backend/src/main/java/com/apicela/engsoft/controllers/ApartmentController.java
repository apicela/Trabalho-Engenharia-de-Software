package com.apicela.engsoft.controllers;

import com.apicela.engsoft.dtos.ApartmentDTO;
import com.apicela.engsoft.models.residencial.Apartment;
import com.apicela.engsoft.services.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController()
@Tag(name = "3. Apartment ", description = "CRUD")
@RequestMapping("/apartments")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin("*")
public class ApartmentController {
    final ApartmentService apartmentService;

    @GetMapping
    @Operation(summary = "Obter todos apartamentos", description = "Here, you can get a list of objects")
    public ResponseEntity<List<Apartment>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(apartmentService.getAll());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Registrar uma novo apartamento",
            description = "Criação de um novo registro de apartamento com imagens.")
    public ResponseEntity<Object> save(
            @Parameter(description = "Imagens da propriedade",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "array", format = "binary")))
            @RequestPart("images") MultipartFile[] images, // Captura as imagens separadamente
            @ModelAttribute @Valid ApartmentDTO apartmentDTO) throws IOException { // Captura o restante dos dados via ModelAttribute

        apartmentDTO.setImages(images);
        return ResponseEntity.status(HttpStatus.CREATED).body(apartmentService.save(apartmentDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar Apartamento", description = "")
    public ResponseEntity<Object> updateEquipment(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid ApartmentDTO apartmentDTO) throws IOException {
        var apartment = apartmentService.update(id, apartmentDTO);
        if (apartment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado objeto para o ID");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(apartment);
        }
    }

}

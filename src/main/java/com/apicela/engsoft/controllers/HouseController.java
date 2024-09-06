package com.apicela.engsoft.controllers;

import com.apicela.engsoft.dtos.HouseDTO;
import com.apicela.engsoft.models.residencial.House;
import com.apicela.engsoft.services.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@Tag(name = "2. House ", description = "CRUD")
@RequestMapping("/house")
@Log4j2
@CrossOrigin("*")
@AllArgsConstructor
public class HouseController {

    final HouseService houseService;

    @GetMapping
    @Operation(summary = "Obter todas casas", description = "Here, you can get a list of objects")
    public ResponseEntity<List<House>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.getAll());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Registrar uma nova casa",
            description = "Criação de um novo registro de casa com imagens.")
    public ResponseEntity<Object> save(
            @Parameter(description = "Imagens da propriedade",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "array", format = "binary")))
            @RequestPart("images") MultipartFile[] images, // Captura as imagens separadamente
            @ModelAttribute @Valid HouseDTO houseDTO) throws IOException { // Captura o restante dos dados via ModelAttribute

        houseDTO.setImages(images);
        return ResponseEntity.status(HttpStatus.CREATED).body(houseService.save(houseDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar House", description = "")
    public ResponseEntity<Object> updateEquipment(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid HouseDTO houseDTO) throws IOException {
        var house = houseService.update(id, houseDTO);
        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado objeto para o ID");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(house);
        }
    }
}

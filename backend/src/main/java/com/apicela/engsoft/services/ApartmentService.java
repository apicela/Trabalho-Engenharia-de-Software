package com.apicela.engsoft.services;

import com.apicela.engsoft.dtos.ApartmentDTO;
import com.apicela.engsoft.models.residencial.Apartment;
import com.apicela.engsoft.repositories.ApartmentRepository;
import com.apicela.engsoft.utils.converter.ApartmentMapper;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ApartmentService {
    final ApartmentRepository apartmentRepository;
    final ApartmentMapper mapper;

    @Transactional
    public Apartment save(ApartmentDTO apartmentDTO) throws IOException {
        var apartment = mapper.mapDtoToEntity(apartmentDTO);
        log.info("Save apartment service");
        return apartmentRepository.save(apartment);
    }

    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json")})
    })


    public Optional<Apartment> findById(UUID id) {
        return apartmentRepository.findById(id);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - deletado com sucesso", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json")})
    })
    public void delete(Apartment house) {
        apartmentRepository.delete(house);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - deletado com sucesso", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json")})
    })
    public Apartment update(UUID id, ApartmentDTO apartmentDTO) throws IOException {
        Optional<Apartment> apartmentOptional = findById(id);
        if (!apartmentOptional.isPresent()) {
            return null;
        }
        var apartment = mapper.mapDtoToEntity(apartmentDTO);
        apartment.setId(id);
        return apartmentRepository.save(apartment);
    }

}

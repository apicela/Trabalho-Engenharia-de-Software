package com.apicela.engsoft.services;

import com.apicela.engsoft.dtos.ApartmentDTO;
import com.apicela.engsoft.models.calendar.Calendar;
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
    final CalendarService calendarService;

    @Transactional
    public Apartment save(ApartmentDTO apartmentDTO) throws IOException {
        var apartment = mapper.mapDtoToEntity(apartmentDTO);
        log.info("Save apartment service");
        var savedObject = apartmentRepository.save(apartment);
        var calendar =  Calendar.builder()
                .propertyId(savedObject.getId()).build();
        calendarService.save(calendar);
        return savedObject;
    }

    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> findById(UUID id) {
        return apartmentRepository.findById(id);
    }

    @Transactional
    public void delete(Apartment house) {
        apartmentRepository.delete(house);
    }

    @Transactional

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

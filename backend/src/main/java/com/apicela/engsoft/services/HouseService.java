package com.apicela.engsoft.services;

import com.apicela.engsoft.dtos.HouseDTO;
import com.apicela.engsoft.models.calendar.Calendar;
import com.apicela.engsoft.models.residencial.House;
import com.apicela.engsoft.repositories.HouseRepository;
import com.apicela.engsoft.utils.converter.HouseMapper;
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
public class HouseService {

    final HouseRepository houseRepository;
    final CalendarService calendarService;
    final HouseMapper mapper;

    @Transactional
    public House save(HouseDTO houseDTO) throws IOException {
        var house = mapper.mapDtoToEntity(houseDTO);
        var savedObject = houseRepository.save(house);
        var calendar =  Calendar.builder()
                        .propertyId(savedObject.getId()).build();
        calendarService.save(calendar);
        return savedObject;
    }

    public List<House> getAll() {
        return houseRepository.findAll();
    }

    public Optional<House> findById(UUID id) {
        return houseRepository.findById(id);
    }

    @Transactional
    public void delete(House house) {
        houseRepository.delete(house);
    }

    @Transactional
    public House update(UUID id, HouseDTO houseDTO) throws IOException {
        Optional<House> houseOptional = findById(id);
        if (!houseOptional.isPresent()) {
            return null;
        }
        var house = mapper.mapDtoToEntity(houseDTO);
        house.setId(id);
        return houseRepository.save(house);
    }

}

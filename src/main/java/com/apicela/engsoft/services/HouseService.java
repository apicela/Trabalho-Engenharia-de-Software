package com.apicela.engsoft.services;

import com.apicela.engsoft.dtos.HouseDTO;
import com.apicela.engsoft.models.residencial.House;
import com.apicela.engsoft.repositories.HouseRepository;
import com.apicela.engsoft.utils.converter.HouseMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class HouseService {

    final HouseRepository houseRepository;
    final HouseMapper mapper;

    @Transactional
    public House save(HouseDTO houseDTO) throws IOException {
        log.info("houseDTO: {}", houseDTO);
        var house = mapper.mapDtoToEntity(houseDTO);
        log.info("houseMapped: {}", house);
        return houseRepository.save(house);
    }

    public List<House> getAll() {
        return houseRepository.findAll();
    }
}

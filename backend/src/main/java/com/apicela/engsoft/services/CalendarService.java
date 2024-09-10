package com.apicela.engsoft.services;

import com.apicela.engsoft.dtos.HouseDTO;
import com.apicela.engsoft.models.calendar.Calendar;
import com.apicela.engsoft.models.residencial.House;
import com.apicela.engsoft.repositories.CalendarRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarService {
    final CalendarRepository calendarRepository;

    public Optional<Calendar> findById(UUID id) {
        return calendarRepository.findById(id);
    }

    @Transactional
    public Calendar update(UUID id, String startTime) throws IOException {
        Optional<Calendar> calendarOptional = findById(id);
        if (!calendarOptional.isPresent()) {
            return null;
        } else {
            var calendar = calendarOptional.get();
            return calendar;
        }
    }
}

package com.apicela.engsoft.services;

import com.apicela.engsoft.models.calendar.Calendar;
import com.apicela.engsoft.repositories.CalendarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class CalendarService {
    final CalendarRepository calendarRepository;

    public Optional<Calendar> findById(UUID id) {
        return calendarRepository.findById(id);
    }

    public Optional<Calendar> findByPropertyId(UUID id) {
        return calendarRepository.findByPropertyId(id);
    }

    public List<Calendar> getAll() {
        return calendarRepository.findAll();
    }

    @Transactional
    public Calendar update(UUID propertyId, String startTime) throws IOException {
        Optional<Calendar> calendarOptional = findByPropertyId(propertyId);
        if (!calendarOptional.isPresent()) {
            return null;
        } else {
            var calendar = calendarOptional.get();
            setAvailability(startTime, calendar);
            return save(calendar);
        }
    }

    @Transactional
    public Calendar save(Calendar calendar) throws IOException {
        return calendarRepository.save(calendar);
    }

    public void setAvailability(String startTimeStr, Calendar calendar) {
        // Remove aspas duplas caso estejam presentes na string
        startTimeStr = startTimeStr.replace("\"", "");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(startTimeStr, formatter);

        // Percorre a lista de TimeSlot para encontrar o startTime
        for (Calendar.TimeSlot slot : calendar.getAvailableTimeSlots()) {
            if (slot.getStartTime().equals(startTime)) {
                slot.setAvailable(false); // Definindo 'available' como false
                break;
            }
        }
    }
}

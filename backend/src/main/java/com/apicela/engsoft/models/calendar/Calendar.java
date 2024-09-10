package com.apicela.engsoft.models.calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Calendar {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID propertyId;

    @ElementCollection
    private List<TimeSlot> availableTimeSlots = new ArrayList<>();

    @PostLoad
    private void initializeAvailableTimeSlots() {
        if (availableTimeSlots == null || availableTimeSlots.isEmpty()) {
            generateAvailableTimeSlots();
        }
    }

    private void generateAvailableTimeSlots() {
        availableTimeSlots.clear();
        LocalTime startMorning = LocalTime.of(9, 0);
        LocalTime endMorning = LocalTime.of(12, 0);
        LocalTime startAfternoon = LocalTime.of(13, 0);
        LocalTime endAfternoon = LocalTime.of(18, 0);

        // Adiciona os intervalos da manhã
        addTimeSlots(startMorning, endMorning);

        // Adiciona os intervalos da tarde
        addTimeSlots(startAfternoon, endAfternoon);
    }

    private void addTimeSlots(LocalTime start, LocalTime end) {
        for (LocalTime time = start; !time.plusHours(1).isAfter(end); time = time.plusHours(1)) {
            availableTimeSlots.add(new TimeSlot(time, time.plusHours(1), true));
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class TimeSlot {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private LocalTime startTime;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private LocalTime endTime;
        private boolean available;
    }
}
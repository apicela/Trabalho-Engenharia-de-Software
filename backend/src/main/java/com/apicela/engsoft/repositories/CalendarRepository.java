package com.apicela.engsoft.repositories;

import com.apicela.engsoft.models.calendar.Calendar;
import com.apicela.engsoft.models.residencial.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, UUID> {


}
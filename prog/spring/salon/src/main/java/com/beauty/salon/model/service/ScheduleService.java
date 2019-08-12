package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    List<Schedule> findMastersByDay(LocalDate date);
    List<Schedule> getScheduleByDateCheck(LocalDate date);
}

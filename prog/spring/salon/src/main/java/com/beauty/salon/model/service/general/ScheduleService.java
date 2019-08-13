package com.beauty.salon.model.service.general;

import com.beauty.salon.model.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    List<Schedule> findMastersByDay(LocalDate date);
}

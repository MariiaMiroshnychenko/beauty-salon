package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findMastersByDay(String dayOfWeek);
}

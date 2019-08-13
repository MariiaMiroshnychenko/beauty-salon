package com.beauty.salon.model.service.general.impl;

import com.beauty.salon.model.entity.Schedule;
import com.beauty.salon.model.repository.ScheduleRepository;
import com.beauty.salon.model.service.general.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    private Map<String, List<Schedule>> schedulesByDayOfWeek() {
        Map<String, List<Schedule>> scheduleByDayOfWeekQuery = new HashMap<>();

        scheduleByDayOfWeekQuery.put("monday", scheduleRepository.findSchedulesByMondayIsTrue());
        scheduleByDayOfWeekQuery.put("tuesday", scheduleRepository.findSchedulesByTuesdayIsTrue());
        scheduleByDayOfWeekQuery.put("wednesday", scheduleRepository.findSchedulesByWednesdayIsTrue());
        scheduleByDayOfWeekQuery.put("thursday", scheduleRepository.findSchedulesByThursdayIsTrue());
        scheduleByDayOfWeekQuery.put("friday", scheduleRepository.findSchedulesByFridayIsTrue());
        scheduleByDayOfWeekQuery.put("saturday", scheduleRepository.findSchedulesBySaturdayIsTrue());
        scheduleByDayOfWeekQuery.put("sunday", scheduleRepository.findSchedulesBySundayIsTrue());

        return scheduleByDayOfWeekQuery;
    }

    private String getDayOfWeek(LocalDate date) {
        return date.getDayOfWeek().toString().toLowerCase();
    }

    @Override
    public List<Schedule> findMastersByDay(LocalDate date) {
        return schedulesByDayOfWeek().get(getDayOfWeek(date));
    }
}

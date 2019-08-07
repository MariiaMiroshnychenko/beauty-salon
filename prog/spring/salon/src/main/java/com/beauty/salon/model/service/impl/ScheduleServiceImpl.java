package com.beauty.salon.model.service.impl;

import com.beauty.salon.model.entity.Schedule;
import com.beauty.salon.model.repository.ScheduleRepository;
import com.beauty.salon.model.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> findMastersByDay(String dayOfWeek) {
        return scheduleRepository.findQueryByDayOfWeek(dayOfWeek);
    }
}

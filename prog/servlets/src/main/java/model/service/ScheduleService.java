package model.service;

import model.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    List<Schedule> findMastersByDay(LocalDate date);
}

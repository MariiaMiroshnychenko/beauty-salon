package model.service;

import model.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findMastersByDay(String date);
}

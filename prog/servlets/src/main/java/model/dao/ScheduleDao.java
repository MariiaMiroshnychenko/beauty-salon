package model.dao;

import model.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleDao extends GenericDao<Schedule> {
    List<Schedule> findMastersByDay(LocalDate date);
}

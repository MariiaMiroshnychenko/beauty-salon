package model.service.impl;

import model.dao.FactoryDao;
import model.dao.ScheduleDao;
import model.entity.Schedule;
import model.service.ScheduleService;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService{
    @Override
    public List<Schedule> findMastersByDay(String date) {
        ScheduleDao scheduleDao = FactoryDao.getInstance().scheduleDao();
        List<Schedule> schedules = scheduleDao.findMastersByDay(date);
        scheduleDao.close();
        return schedules;
    }
}

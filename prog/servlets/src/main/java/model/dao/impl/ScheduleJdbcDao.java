package model.dao.impl;

import model.dao.ScheduleDao;
import model.entity.Record;
import model.entity.Schedule;
import model.mapper.impl.RecordMapper;
import model.mapper.impl.ScheduleMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleJdbcDao implements ScheduleDao {
    private ScheduleMapper scheduleMapper = new ScheduleMapper();
    private Map<Integer, Schedule> scheduleMap = new HashMap<>();
    private Connection connection;

    public ScheduleJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Schedule schedule) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Schedule> findMastersByDay(LocalDate date) {
        List<Schedule> schedules = new ArrayList<>();

        String dayOfWeek = date.getDayOfWeek().toString().toLowerCase();

        try (PreparedStatement statement = connection.prepareStatement("select * from `schedule_table` where " + dayOfWeek + " = true")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Schedule schedule = scheduleMapper.extractFromResultSet(resultSet);

                scheduleMapper.makeUnique(scheduleMap, schedule);
            }
            resultSet.close();
            schedules = new ArrayList<>(scheduleMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }
}

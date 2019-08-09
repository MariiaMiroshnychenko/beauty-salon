package model.mapper.impl;

import model.entity.Schedule;
import model.mapper.MainMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ScheduleMapper implements MainMapper<Schedule> {
    @Override
    public Schedule extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer masterId = resultSet.getInt("master_id");
        Boolean sunday = resultSet.getBoolean("sunday");
        Boolean monday = resultSet.getBoolean("monday");
        Boolean tuesday = resultSet.getBoolean("tuesday");
        Boolean wednesday = resultSet.getBoolean("wednesday");
        Boolean thursday = resultSet.getBoolean("thursday");
        Boolean friday = resultSet.getBoolean("friday");
        Boolean saturday = resultSet.getBoolean("saturday");

        return new Schedule(id, masterId, sunday, monday, tuesday, wednesday, thursday, friday, saturday);
    }

    @Override
    public Schedule makeUnique(Map<Integer, Schedule> cache, Schedule entity) {
        cache.putIfAbsent(entity.getMasterId(), entity);
        return cache.get(entity.getMasterId());
    }
}

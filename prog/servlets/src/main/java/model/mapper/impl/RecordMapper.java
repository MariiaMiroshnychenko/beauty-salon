package model.mapper.impl;

import model.entity.Record;
import model.mapper.MainMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class RecordMapper implements MainMapper<Record> {
    @Override
    public Record extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer master_id = resultSet.getInt("master_id");
        Integer procedure_id = resultSet.getInt("procedure_id");
        LocalDate date = resultSet.getDate("record_date").toLocalDate();
        LocalTime time = resultSet.getTime("time").toLocalTime();
        Integer clientId = resultSet.getInt("client_id");

        return new Record(id, master_id, procedure_id, date, time, clientId);
    }
    @Override
    public Record makeUnique(Map<Integer, Record> cache, Record entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

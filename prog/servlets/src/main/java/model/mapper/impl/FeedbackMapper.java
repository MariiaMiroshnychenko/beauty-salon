package model.mapper.impl;

import model.entity.Feedback;
import model.mapper.MainMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class FeedbackMapper implements MainMapper<Feedback> {
    @Override
    public Feedback extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer clientId = resultSet.getInt("client_id");
        Integer masterId = resultSet.getInt("master_id");
        Integer recordId = resultSet.getInt("record_id");
        String text = resultSet.getString("text");
        LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();

        return new Feedback(id, clientId, masterId, recordId, text, dateTime);
    }

    @Override
    public Feedback makeUnique(Map<Integer, Feedback> cache, Feedback entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

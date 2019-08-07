package model.mapper.impl;

import model.entity.Review;
import model.mapper.MainMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class ReviewMapper implements MainMapper<Review> {
    @Override
    public Review extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer clientId = resultSet.getInt("client_id");
        Integer masterId = resultSet.getInt("master_id");
        Integer recordId = resultSet.getInt("record_id");
        String text = resultSet.getString("text");
        LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();

        return new Review(id, clientId, masterId, recordId, text, dateTime);
    }

    @Override
    public Review makeUnique(Map<Integer, Review> cache, Review entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

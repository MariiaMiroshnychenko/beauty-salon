package model.mapper.impl;

import model.entity.Procedure;
import model.mapper.MainMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProcedureMapper implements MainMapper<Procedure> {
    @Override
    public Procedure extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer code = resultSet.getInt("code");
        String name = resultSet.getString("name");
        Integer languageId = resultSet.getInt("language_id");

        return new Procedure(id, code, name, languageId);
    }

    @Override
    public Procedure makeUnique(Map<Integer, Procedure> cache, Procedure entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

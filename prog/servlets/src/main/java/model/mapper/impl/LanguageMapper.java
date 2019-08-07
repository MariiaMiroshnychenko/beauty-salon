package model.mapper.impl;

import model.entity.Language;
import model.mapper.MainMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class LanguageMapper implements MainMapper<Language> {
    @Override
    public Language extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String locale = resultSet.getString("locale");

        return new Language(id, locale);
    }

    @Override
    public Language makeUnique(Map<Integer, Language> cache, Language entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}

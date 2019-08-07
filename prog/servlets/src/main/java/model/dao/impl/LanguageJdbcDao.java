package model.dao.impl;

import model.dao.LanguageDao;
import model.entity.Language;
import model.entity.Record;
import model.mapper.impl.LanguageMapper;
import model.mapper.impl.RecordMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LanguageJdbcDao implements LanguageDao {
    private LanguageMapper languageMapper = new LanguageMapper();
    private Map<Integer, Language> languageMap = new HashMap<>();

    private Connection connection;

    public LanguageJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Language language) {

    }

    @Override
    public void update(Language language) {

    }

    @Override
    public void delete() {

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
    public Language findLanguageByLocale(String locale) {
        Language language = null;

        try (PreparedStatement statement = connection.prepareStatement("select * from `language` where locale=?")) {
            statement.setString(1, locale);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                language = languageMapper.extractFromResultSet(resultSet);
            }
            if (Objects.nonNull(language)) {
                languageMapper.makeUnique(languageMap, language);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return language;
    }
}

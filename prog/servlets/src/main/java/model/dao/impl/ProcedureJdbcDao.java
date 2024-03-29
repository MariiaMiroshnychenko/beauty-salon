package model.dao.impl;

import model.dao.LanguageDao;
import model.dao.ProcedureDao;
import model.entity.Language;
import model.entity.Procedure;
import model.entity.Record;
import model.mapper.impl.ProcedureMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProcedureJdbcDao implements ProcedureDao {
    private ProcedureMapper procedureMapper = new ProcedureMapper();
    private Map<Integer, Procedure> procedureMap = new HashMap<>();

    private Connection connection;

    public ProcedureJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Procedure procedure) {
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Procedure findByAttributes (String query, Object... attributes) {
        Procedure procedure = null;

        int counter = 1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Object attribute : attributes) {
                statement.setObject(counter, attribute);
                counter++;
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                procedure = procedureMapper.extractFromResultSet(resultSet);
            }
            if (Objects.nonNull(procedure)) {
                procedureMapper.makeUnique(procedureMap, procedure);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return procedure;
    }
    @Override
    public Procedure findProcedureById(Integer id) {
        return findByAttributes("select * from `procedure_table` where id=?", id);
    }

    @Override
    public List<Procedure> findProceduresByLanguageId(Integer languageId) {
        List<Procedure> records = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("select * from `procedure_table` where language_id=?")) {
            statement.setInt(1, languageId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Procedure record = procedureMapper.extractFromResultSet(resultSet);

                procedureMapper.makeUnique(procedureMap, record);
            }
            resultSet.close();
            records = new ArrayList<>(procedureMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public Procedure findProcedureByCodeAndLanguageId(Integer procedureCode, Integer languageId) {
        return findByAttributes("select * from `procedure_table` where code=? and language_id=?", procedureCode,languageId);
    }
}

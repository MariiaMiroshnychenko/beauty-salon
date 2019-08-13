package model.dao.impl;

import model.dao.RecordDao;
import model.entity.Record;
import model.mapper.impl.RecordMapper;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RecordJdbcDao implements RecordDao {
    private RecordMapper recordMapper = new RecordMapper();
    private Map<Integer, Record> recordMap = new HashMap<>();
    private Connection connection;

    public RecordJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Record record) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO record_table (master_id, procedure_id, record_date, `time`, client_id) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, record.getMasterId());
            statement.setInt(2, record.getProcedureId());
            statement.setDate(3, Date.valueOf(record.getRecordDate()));
            statement.setTime(4, Time.valueOf(record.getTime()));
            statement.setInt(5, record.getClientId());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public List<Record> executeQuery(String query, Object... queryParameters) {
        List<Record> records = new ArrayList<>();

        int counter = 1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (Object parameter : queryParameters) {
                statement.setObject(counter, parameter);
                counter++;
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Record record = recordMapper.extractFromResultSet(resultSet);
                recordMapper.makeUnique(recordMap, record);
            }

            resultSet.close();

            records = new ArrayList<>(recordMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<Record> findRecordsByDateAndMasterId(LocalDate date, Integer masterId) {
        return executeQuery("select * from record_table where record_date=? and master_id=?", date, masterId);
    }

    @Override
    public List<Record> findRecordsByUserId(Integer userId, String userRole) {
        String query = "select * from record_table where " + userRole + "_id=?";
        return executeQuery(query, userId);
    }

    @Override
    public List<Record> findRecordsByDateAndTimeWithCondition(LocalDate date, LocalTime time, String query) {
        return executeQuery(query, date, date, time);
    }

    @Override
    public List<Record> findRecordsByDate(LocalDate date) {
        return executeQuery("select * from record_table where record_date=?", date);
    }

    @Override
    public List<Record> findRecordsByDateAndTime(LocalDate date, LocalTime time) {
        return executeQuery("select * from record_table where record_date=? and time=?", date, time);
    }

    @Override
    public Record findRecordById(Integer recordId) {
        Record record = null;

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM record_table WHERE id=?")) {
            statement.setInt(1, recordId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                record = recordMapper.extractFromResultSet(resultSet);
            }
            if (Objects.nonNull(record)) {
                recordMapper.makeUnique(recordMap, record);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }
}

package model.dao;

import model.entity.Record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordDao extends GenericDao<Record> {
    List<Record> findRecordsByDateAndMasterId(String date, Integer masterId);
    List<Record> findRecordsByUserId(Integer userId, String userRole);
    List<Record> findRecordsByDateAndTimeWithCondition(String date, LocalTime time, String query);
    List<Record> findRecordsByDateAndTime(String date, LocalTime time);
    List<Record> findRecordsByDate(String date);
    Record findRecordById(Integer recordId);

    List<Record> executeQuery(String query, Object... parameters);
}

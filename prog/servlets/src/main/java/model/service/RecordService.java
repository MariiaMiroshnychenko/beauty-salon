package model.service;

import model.entity.Record;

import java.time.LocalTime;
import java.util.List;

public interface RecordService {
    List<Record> findRecordsByDateAndMasterId(String date, Integer masterId);
    List<Record> findRecordsByUserId(Integer userId, String userRole);
    List<Record> findRecordsByDateAndTimeWithCondition(String date, LocalTime time, String query);
    List<Record> findRecordsByDateAndTime(String date, LocalTime time);
    List<Record> findRecordsByDate(String date);

    List<Record> executeQuery(String query, Object... parameters);

    Record findRecordById(Integer recordId);
    void create(Record record);
}


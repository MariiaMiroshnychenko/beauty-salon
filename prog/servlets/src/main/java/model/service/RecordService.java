package model.service;

import model.entity.Record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordService {
    List<Record> findRecordsByDateAndMasterId(LocalDate date, Integer masterId);
    List<Record> findRecordsByUserId(Integer userId, String userRole);
    List<Record> findRecordsByDateAndTimeWithCondition(LocalDate date, LocalTime time, String query);
    List<Record> findRecordsByDateAndTime(LocalDate date, LocalTime time);
    List<Record> findRecordsByDate(LocalDate date);

    List<Record> executeQuery(String query, Object... parameters);

    Record findRecordById(Integer recordId);
    void create(Record record);
}


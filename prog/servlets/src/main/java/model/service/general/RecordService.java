package model.service.general;

import model.entity.Record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordService {
    List<Record> findRecordsByDateAndMasterId(LocalDate date, Integer masterId);
    List<Record> findRecordsByUserId(Integer userId, String userRole);
    List<Record> findRecordsByDateAndTime(LocalDate date, LocalTime time);

    List<Record> executeQuery(String query, Object... parameters);

    Record findRecordById(Integer recordId);
    void create(Record record);
}


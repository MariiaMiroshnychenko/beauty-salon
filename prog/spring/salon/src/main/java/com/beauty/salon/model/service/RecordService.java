package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordService {
    List<Record> findRecordsByDateAndMasterId(LocalDate date, Integer masterId);
    List<Record> findRecordsByMaster(User master);
    List<Record> findRecordsByDateAndTime(LocalDate date, LocalTime time);
    List<Record> findRecordsByDate(LocalDate date);

    Record findRecordById(Integer recordId);
    void create(Record record);
}


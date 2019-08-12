package com.beauty.salon.model.service.impl;

import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.repository.RecordRepository;
import com.beauty.salon.model.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void create(Record record) {
        recordRepository.saveAndFlush(record);
    }

    @Override
    public List<Record> findRecordsByDateAndMasterId(String date, Integer masterId) {
        return recordRepository.findRecordsByRecordDateAndMasterId(date, masterId);
    }

    @Override
    public List<Record> findRecordsByMaster(User master) {
        return recordRepository.findRecordsByMasterId(master);
    }

    @Override
    public List<Record> findRecordsByClientIdAndDateAndTimeLessNow(User user, LocalDate dateNow, LocalTime time) {
        return recordRepository.findRecordsByClientIdAndRecordDateBeforeOrRecordDateEqualsAndTimeBefore(user, dateNow, dateNow, time);
    }

    @Override
    public List<Record> findRecordsByClientIdAndDateAndTimeMoreNow(User user, LocalDate date, LocalTime time) {
        return recordRepository.findRecordsByClientIdAndRecordDateAfterOrRecordDateEqualsAndTimeAfter(user, date, date, time);
    }

    @Override
    public List<Record> findRecordsByDateAndTime(String date, LocalTime time) {
        return recordRepository.findRecordsByRecordDateAndTime(date, time);
    }

    @Override
    public List<Record> findRecordsByDate(LocalDate date) {
        return recordRepository.findRecordsByRecordDate(date);
    }

    @Override
    public Record findRecordById(Integer recordId) {
        return recordRepository.getOne(recordId);
    }
}

package com.beauty.salon.model.service.general.impl;

import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.repository.RecordRepository;
import com.beauty.salon.model.service.general.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Record findRecordById(Integer recordId) {
        return recordRepository.getOne(recordId);
    }
}

package com.beauty.salon.model.service.general;

import com.beauty.salon.model.entity.Record;

public interface RecordService {
    Record findRecordById(Integer recordId);
    void create(Record record);
}


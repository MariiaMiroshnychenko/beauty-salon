package com.beauty.salon.model.service.user;

import com.beauty.salon.model.entity.Record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface MasterService {
    Map<LocalTime, Record> masterSchedule(LocalDate date, Integer masterId, String locale);
}

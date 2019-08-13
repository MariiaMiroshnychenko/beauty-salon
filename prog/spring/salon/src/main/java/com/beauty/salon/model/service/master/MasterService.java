package com.beauty.salon.model.service.master;

import com.beauty.salon.model.entity.Record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface MasterService {
    Map<LocalTime, Record> masterSchedule(LocalDate date, Integer masterId, String locale,
                                          int beginHour, int endHour, int minute);
}

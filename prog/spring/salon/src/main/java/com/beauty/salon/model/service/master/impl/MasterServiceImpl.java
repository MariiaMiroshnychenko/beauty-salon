package com.beauty.salon.model.service.master.impl;

import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.repository.RecordRepository;
import com.beauty.salon.model.service.master.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MasterServiceImpl implements MasterService {
    private RecordRepository recordRepository;
    private ProcedureRepository procedureRepository;

    @Autowired
    public MasterServiceImpl(RecordRepository recordRepository,
                             ProcedureRepository procedureRepository) {
        this.recordRepository = recordRepository;
        this.procedureRepository = procedureRepository;
    }

    private void setProcedureByLocale(List<Record> records, String locale) {
        records.forEach(record -> record.setProcedureId(
                procedureRepository.findProcedureByCodeAndLanguageId_Locale(
                        record.getProcedureId().getCode(), locale)));
    }

    private List<Record> masterRecordsByDateAndMasterId(String date, Integer masterId, String locale) {
        List<Record> masterRecords = recordRepository.findRecordsByRecordDateAndMasterId(date, masterId);

        setProcedureByLocale(masterRecords, locale);

        return masterRecords;
    }

    private void scheduleFillByTime(Map<LocalTime, Record> scheduleMap, List<Record> records,
                                    int beginHour, int endHour, int minute) {
        records.forEach(record -> scheduleMap.put(record.getTime(), record));

        while (beginHour <= endHour) {
            scheduleMap.putIfAbsent(LocalTime.of(beginHour, minute), null);
            beginHour++;
        }
    }

    @Override
    public Map<LocalTime, Record> masterSchedule(String date, Integer masterId, String locale,
                                                 int beginHour, int endHour, int minute) {
        Map<LocalTime, Record> recordMap = new TreeMap<>();

        scheduleFillByTime(recordMap, masterRecordsByDateAndMasterId(date, masterId, locale),
                beginHour, endHour, minute);

        return recordMap;
    }
}

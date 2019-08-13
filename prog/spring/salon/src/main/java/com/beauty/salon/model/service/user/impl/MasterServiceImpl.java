package com.beauty.salon.model.service.user.impl;

import com.beauty.salon.container.ConstantWorkHour;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.repository.RecordRepository;
import com.beauty.salon.model.service.user.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    private List<Record> masterRecordsByDateAndMasterId(LocalDate date, Integer masterId, String locale) {
        List<Record> masterRecords = recordRepository.findRecordsByRecordDateAndMasterId_Id(date, masterId);

        setProcedureByLocale(masterRecords, locale);

        return masterRecords;
    }

    private void scheduleFillByTime(Map<LocalTime, Record> scheduleMap, List<Record> records) {
        int beginHour = ConstantWorkHour.BEGIN_HOUR;
        int endHour = ConstantWorkHour.END_HOUR;
        int minute = ConstantWorkHour.MINUTE;

        records.forEach(record -> scheduleMap.put(record.getTime(), record));

        while (beginHour <= endHour) {
            scheduleMap.putIfAbsent(LocalTime.of(beginHour, minute), null);
            beginHour++;
        }
    }

    @Override
    public Map<LocalTime, Record> masterSchedule(LocalDate date, Integer masterId, String locale) {
        Map<LocalTime, Record> recordMap = new TreeMap<>();

        scheduleFillByTime(recordMap, masterRecordsByDateAndMasterId(date, masterId, locale));

        return recordMap;
    }
}

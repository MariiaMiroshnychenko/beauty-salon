package model.service.user.impl;

import container.ConstantWorkHour;
import model.entity.Record;
import model.entity.User;
import model.service.general.LanguageService;
import model.service.general.ProcedureService;
import model.service.general.RecordService;
import model.service.general.UserService;
import model.service.general.impl.LanguageServiceImpl;
import model.service.general.impl.ProcedureServiceImpl;
import model.service.general.impl.RecordServiceImpl;
import model.service.general.impl.UserServiceImpl;
import model.service.user.MasterService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MasterServiceImpl implements MasterService {
    private void putRecords(List<Record> records, Map<LocalTime, Record> recordMap) {
        records.forEach(record -> recordMap.put(record.getTime(), record));
    }

    private void setRecordsParameters(List<Record> records, String language) {
        ProcedureService procedureService = new ProcedureServiceImpl();
        LanguageService languageService = new LanguageServiceImpl();
        UserService userService = new UserServiceImpl();

        records.forEach(record -> {
            record.setProcedure(
                    procedureService.findProcedureByCodeAndLanguageId(
                            procedureService.findProcedureById(record.getProcedureId()).getCode(),
                            languageService.findLanguageByLocale(language).getId()));

            record.setClient(userService.findUserById(record.getClientId()));
        });
    }

    private List<Record> masterRecords(String date, User user, String language) {
        RecordService recordService = new RecordServiceImpl();

        List<Record> records = recordService.findRecordsByDateAndMasterId(LocalDate.parse(date), user.getId());

        setRecordsParameters(records, language);

        return records;
    }

    private void putAbsentTimes(Map<LocalTime, Record> recordMap) {
        int beginHour = ConstantWorkHour.BEGIN_HOUR;
        int endHour = ConstantWorkHour.END_HOUR;
        int minute = ConstantWorkHour.MINUTE;

        while (beginHour <= endHour) {
            recordMap.putIfAbsent(LocalTime.of(beginHour, minute), null);
            beginHour++;
        }
    }

    @Override
    public Map<LocalTime, Record> masterScheduleMap(String date, User user, String language) {
        Map<LocalTime, Record> recordMap = new TreeMap<>();

        putRecords(masterRecords(date, user, language), recordMap);
        putAbsentTimes(recordMap);

        return recordMap;
    }
}

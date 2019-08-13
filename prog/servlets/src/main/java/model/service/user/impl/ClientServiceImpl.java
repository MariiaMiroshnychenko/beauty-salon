package model.service.user.impl;

import model.entity.Record;
import model.entity.Schedule;
import model.entity.User;
import model.service.*;
import model.service.impl.*;
import model.service.user.ClientService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {
    private List<LocalTime> workingTimeList(int beginHour, int endHour, int minute) {
        List<LocalTime> timeList = new ArrayList<>();

        LocalTime availableTime;
        while (beginHour <= endHour) {
            availableTime = LocalTime.of(beginHour, minute);
            timeList.add(availableTime);
            beginHour++;
        }

        return timeList;
    }

    private List<LocalTime> unavailableTimeList(LocalDate date, String masterId) {
        RecordService recordService = new RecordServiceImpl();

        List<LocalTime> unavailableTimes = new ArrayList<>();
        List<Record> recordList = recordService.findRecordsByDateAndMasterId(date, Integer.valueOf(masterId));

        recordList.forEach(record -> unavailableTimes.add(record.getTime()));

        return unavailableTimes;
    }

    private void setRecordsParameters(List<Record> records, String language) {
        ProcedureService procedureService = new ProcedureServiceImpl();
        LanguageService languageService = new LanguageServiceImpl();
        UserService userService = new UserServiceImpl();

        records.forEach(record -> record.setProcedure(
                procedureService.findProcedureByCodeAndLanguageId(
                        procedureService.findProcedureById(record.getProcedureId()).getCode(),
                        languageService.findLanguageByLocale(language).getId())));

        records.forEach(record -> record.setMaster(userService.findUserById(record.getMasterId())));
        records.forEach(record -> record.setClient(userService.findUserById(record.getClientId())));
    }

    @Override
    public List<LocalTime> availableTime(LocalDate date, String masterId, int beginHour, int endHour, int minute) {
        return workingTimeList(beginHour, endHour, minute)
                .stream().filter(time -> !unavailableTimeList(date, masterId).contains(time) ||
                        date.equals(LocalDate.now()) && time.compareTo(LocalTime.now()) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> masterList(LocalDate date) {
        ScheduleService scheduleService = new ScheduleServiceImpl();
        UserService userService = new UserServiceImpl();

        List<Schedule> schedules = scheduleService.findMastersByDay(date);
        List<User> masters = new ArrayList<>();

        schedules.forEach(schedule -> masters.add(userService.findUserById(schedule.getMasterId())));

        return masters;
    }

    @Override
    public List<Record> futureOrPastRecords(User user, String language, String query) {
        RecordService recordService = new RecordServiceImpl();

        List<Record> futureRecords = recordService.executeQuery(query,
                user.getId(), LocalDate.now(), LocalDate.now(), LocalTime.now());

        setRecordsParameters(futureRecords, language);

        return futureRecords;
    }

    @Override
    public List<Record> uncheckedRecords(User user, String language) {
        RecordService recordService = new RecordServiceImpl();
        FeedbackService feedbackService = new FeedbackServiceImpl();

        List<Record> records = recordService.findRecordsByUserId(user.getId(), user.getRole());

        setRecordsParameters(records, language);

        return records.stream().filter(record -> Objects.isNull(feedbackService.findReviewByRecordId(record.getId())))
                .collect(Collectors.toList());
    }
}

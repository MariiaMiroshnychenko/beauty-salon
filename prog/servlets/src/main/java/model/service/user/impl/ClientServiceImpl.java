package model.service.user.impl;

import model.dao.*;
import model.entity.Record;
import model.entity.Schedule;
import model.entity.User;
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
        RecordDao recordDao = FactoryDao.getInstance().recordDao();

        List<LocalTime> unavailableTimes = new ArrayList<>();
        List<Record> recordList = recordDao.findRecordsByDateAndMasterId(date, Integer.valueOf(masterId));

        recordList.forEach(record -> unavailableTimes.add(record.getTime()));

        return unavailableTimes;
    }

    private void setRecordsParameters(List<Record> records, String language) {
        ProcedureDao procedureDao = FactoryDao.getInstance().procedureDao();
        LanguageDao languageDao = FactoryDao.getInstance().languageDao();
        UserDao userDao = FactoryDao.getInstance().userDao();

        records.forEach(record -> record.setProcedure(
                procedureDao.findProcedureByCodeAndLanguageId(
                        procedureDao.findProcedureById(record.getProcedureId()).getCode(),
                        languageDao.findLanguageByLocale(language).getId())));

        records.forEach(record -> record.setMaster(userDao.findUserById(record.getMasterId())));
        records.forEach(record -> record.setClient(userDao.findUserById(record.getClientId())));
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
        ScheduleDao scheduleDao = FactoryDao.getInstance().scheduleDao();
        UserDao userDao = FactoryDao.getInstance().userDao();

        List<Schedule> schedules = scheduleDao.findMastersByDay(date);
        List<User> masters = new ArrayList<>();

        schedules.forEach(schedule -> masters.add(userDao.findUserById(schedule.getMasterId())));

        return masters;
    }

    @Override
    public List<Record> futureOrPastRecords(User user, String language, String query) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();

        List<Record> futureRecords = recordDao.executeQuery(query,
                user.getId(), LocalDate.now().toString(), LocalTime.now());

        setRecordsParameters(futureRecords, language);

        return futureRecords;
    }

    @Override
    public List<Record> uncheckedRecords(User user, String language) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        FeedbackDao feedbackDao = FactoryDao.getInstance().reviewDao();

        List<Record> records = recordDao.findRecordsByUserId(user.getId(), user.getRole());

        setRecordsParameters(records, language);

        return records.stream().filter(record -> Objects.isNull(feedbackDao.findReviewByRecordId(record.getId())))
                .collect(Collectors.toList());
    }
}

package model.service.user.impl;

import container.ConstantWorkHour;
import model.dao.*;
import model.entity.Feedback;
import model.entity.Record;
import model.service.user.AdminService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AdminServiceImpl implements AdminService {
    private void setFeedbackParameters(List<Feedback> reviewsForMasterId, String language) {
        RecordDao recordDao = FactoryDao.getInstance().recordDao();
        LanguageDao languageDao = FactoryDao.getInstance().languageDao();
        ProcedureDao procedureDao = FactoryDao.getInstance().procedureDao();
        UserDao userDao = FactoryDao.getInstance().userDao();

        reviewsForMasterId.forEach(review -> {
            review.setClient(userDao.findUserById(review.getClientId()));
            review.setMaster(userDao.findUserById(review.getMasterId()));
            review.setRecord(recordDao.findRecordById(review.getRecordId()));
        });

        reviewsForMasterId.forEach(review ->
                review.getRecord().setProcedure(procedureDao.findProcedureByCodeAndLanguageId(
                        procedureDao.findProcedureById(review.getRecord().getProcedureId()).getCode(),
                        languageDao.findLanguageByLocale(language).getId())
                ));
    }

    @Override
    public List<Feedback> feedbackForMaster(Integer masterId, String language) {
        FeedbackDao feedbackDao = FactoryDao.getInstance().reviewDao();
        List<Feedback> reviewsForMasterId = feedbackDao.findReviewsByMasterId(masterId);

        setFeedbackParameters(reviewsForMasterId, language);

        return reviewsForMasterId;
    }

    @Override
    public Map<LocalTime, List<Record>> sortedRecordsByTime(LocalDate date, String locale) {
        int beginHour = ConstantWorkHour.BEGIN_HOUR;
        int endHour = ConstantWorkHour.END_HOUR;
        int minute = ConstantWorkHour.MINUTE;

        RecordDao recordDao = FactoryDao.getInstance().recordDao();

        LocalTime time;
        List<Record> recordsByDateAndTime;
        Map<LocalTime, List<Record>> recordMap = new TreeMap<>();

        while (beginHour <= endHour) {
            time = LocalTime.of(beginHour, minute);
            recordsByDateAndTime = recordDao.findRecordsByDateAndTime(date, time);

            setRecordsParameters(recordsByDateAndTime, locale);

            recordMap.put(time, recordsByDateAndTime);
            beginHour++;
        }

        return recordMap;
    }

    public void setRecordsParameters(List<Record> recordsForDay, String language) {
        UserDao userDao = FactoryDao.getInstance().userDao();
        ProcedureDao procedureDao = FactoryDao.getInstance().procedureDao();
        LanguageDao languageDao = FactoryDao.getInstance().languageDao();

        recordsForDay.forEach(record -> record.setMaster(userDao.findUserById(record.getMasterId())));

        recordsForDay.forEach(record ->
                record.setProcedure(procedureDao.findProcedureByCodeAndLanguageId(
                        procedureDao.findProcedureById(record.getProcedureId()).getCode(),
                        languageDao.findLanguageByLocale(language).getId())));
    }
}


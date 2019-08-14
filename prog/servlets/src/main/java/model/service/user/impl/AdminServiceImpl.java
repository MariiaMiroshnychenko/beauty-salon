package model.service.user.impl;

import container.ConstantWorkHour;
import model.entity.Feedback;
import model.entity.Record;
import model.service.general.*;
import model.service.general.impl.*;
import model.service.user.AdminService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AdminServiceImpl implements AdminService {
    private void setFeedbackParameters(List<Feedback> reviewsForMasterId, String language) {
        RecordService recordService = new RecordServiceImpl();
        LanguageService languageService = new LanguageServiceImpl();
        ProcedureService procedureService = new ProcedureServiceImpl();
        UserService userService = new UserServiceImpl();

        reviewsForMasterId.forEach(review -> {
            review.setClient(userService.findUserById(review.getClientId()));
            review.setMaster(userService.findUserById(review.getMasterId()));
            review.setRecord(recordService.findRecordById(review.getRecordId()));
        });

        reviewsForMasterId.forEach(review ->
                review.getRecord().setProcedure(procedureService.findProcedureByCodeAndLanguageId(
                        procedureService.findProcedureById(review.getRecord().getProcedureId()).getCode(),
                        languageService.findLanguageByLocale(language).getId())
                ));
    }

    private void setRecordsParameters(List<Record> recordsForDay, String language) {
        UserService userService = new UserServiceImpl();
        ProcedureService procedureService = new ProcedureServiceImpl();
        LanguageService languageService = new LanguageServiceImpl();

        recordsForDay.forEach(record -> record.setMaster(userService.findUserById(record.getMasterId())));

        recordsForDay.forEach(record ->
                record.setProcedure(procedureService.findProcedureByCodeAndLanguageId(
                        procedureService.findProcedureById(record.getProcedureId()).getCode(),
                        languageService.findLanguageByLocale(language).getId())));
    }

    @Override
    public List<Feedback> feedbackForMaster(Integer masterId, String language) {
        FeedbackService feedbackService = new FeedbackServiceImpl();

        List<Feedback> reviewsForMasterId = feedbackService.findReviewByMasterId(masterId);

        setFeedbackParameters(reviewsForMasterId, language);

        return reviewsForMasterId;
    }

    @Override
    public Map<LocalTime, List<Record>> sortedRecordsByTime(LocalDate date, String locale) {
        int beginHour = ConstantWorkHour.BEGIN_HOUR;
        int endHour = ConstantWorkHour.END_HOUR;
        int minute = ConstantWorkHour.MINUTE;

        RecordService recordService = new RecordServiceImpl();

        LocalTime time;
        List<Record> recordsByDateAndTime;
        Map<LocalTime, List<Record>> recordMap = new TreeMap<>();

        while (beginHour <= endHour) {
            time = LocalTime.of(beginHour, minute);
            recordsByDateAndTime = recordService.findRecordsByDateAndTime(date, time);

            setRecordsParameters(recordsByDateAndTime, locale);

            recordMap.put(time, recordsByDateAndTime);
            beginHour++;
        }

        return recordMap;
    }
}


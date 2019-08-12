package com.beauty.salon.model.service.admin.impl;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.repository.FeedbackRepository;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.repository.RecordRepository;
import com.beauty.salon.model.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AdminServiceImpl implements AdminService {
    private FeedbackRepository feedbackRepository;
    private RecordRepository recordRepository;
    private ProcedureRepository procedureRepository;

    @Autowired
    public AdminServiceImpl(FeedbackRepository feedbackRepository,
                            RecordRepository recordRepository,
                            ProcedureRepository procedureRepository) {
        this.feedbackRepository = feedbackRepository;
        this.recordRepository = recordRepository;
        this.procedureRepository = procedureRepository;
    }

    private void setProcedureFeedbackByLocale(List<Feedback> feedback, String locale) {
        feedback.forEach(fb ->
                fb.getRecordId().setProcedureId(
                        procedureRepository.findProcedureByCodeAndLanguageId_Locale(
                                fb.getRecordId().getProcedureId().getCode(), locale)));
    }

    private void setRecordProcedureByLocale(List<Record> records, String locale) {
        records.forEach(record ->
                record.setProcedureId(procedureRepository.findProcedureByCodeAndLanguageId_Locale(
                        record.getProcedureId().getCode(), locale)));
    }

    @Override
    public List<Feedback> findFeedbacksByMasterId(Integer masterId, String locale) {
        List<Feedback> feedbackForMasterId = feedbackRepository.findAllByMasterId_Id(masterId);

        setProcedureFeedbackByLocale(feedbackForMasterId, locale);

        return feedbackForMasterId;
    }

    @Override
    public Map<LocalTime, List<Record>> sortedRecordsByTime(String date, String locale) {
        int beginHour = 10;
        int endHour = 18;
        int minute = 0;

        LocalTime time;
        List<Record> recordsByDateAndTime;
        Map<LocalTime, List<Record>> recordMap = new TreeMap<>();

        while (beginHour <= endHour) {
            time = LocalTime.of(beginHour, minute);
            recordsByDateAndTime = recordRepository.findRecordsByRecordDateAndTime(date, time);

            setRecordProcedureByLocale(recordsByDateAndTime, locale);

            recordMap.put(time, recordsByDateAndTime);
            beginHour++;
        }

        return recordMap;
    }
}

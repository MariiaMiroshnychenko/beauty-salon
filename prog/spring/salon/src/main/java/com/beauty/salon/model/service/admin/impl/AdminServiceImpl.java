package com.beauty.salon.model.service.admin.impl;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.repository.FeedbackRepository;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private FeedbackRepository feedbackRepository;
    private ProcedureRepository procedureRepository;

    @Autowired
    public AdminServiceImpl(FeedbackRepository feedbackRepository,
                            ProcedureRepository procedureRepository) {
        this.feedbackRepository = feedbackRepository;
        this.procedureRepository = procedureRepository;
    }

    private void setProcedureFeedbackByLocale(List<Feedback> feedback, String locale) {
        feedback.forEach(fb ->
                fb.getRecordId().setProcedureId(
                        procedureRepository.findProceduresByCodeAndLanguageId_Locale(
                                fb.getRecordId().getProcedureId().getCode(), locale)));
    }

    @Override
    public List<Feedback> findFeedbackMasterById(Integer masterId, String locale) {
        List<Feedback> feedbackForMasterId = feedbackRepository.findFeedbackByMasterId_Id(masterId);

        setProcedureFeedbackByLocale(feedbackForMasterId, locale);
        return feedbackForMasterId;
    }
}

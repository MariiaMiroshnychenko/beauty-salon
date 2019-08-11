package com.beauty.salon.model.service.admin.impl;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.repository.FeedbackRepository;
import com.beauty.salon.model.repository.ProcedureRepository;
import com.beauty.salon.model.repository.UserRepository;
import com.beauty.salon.model.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private FeedbackRepository feedbackRepository;
    private ProcedureRepository procedureRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(FeedbackRepository feedbackRepository,
                            ProcedureRepository procedureRepository,
                            UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.procedureRepository = procedureRepository;
        this.userRepository= userRepository;
    }

    private void setProcedureFeedbackByLocale(List<Feedback> feedback, String locale) {
        feedback.forEach(fb ->
                fb.getRecordId().setProcedureId(
                        procedureRepository.findProcedureByCodeAndLanguageId_Locale(
                                fb.getRecordId().getProcedureId().getCode(), locale)));
    }

    @Override
    public List<Feedback> findFeedbacksByMasterId(Integer masterId, String locale) {
//        User master = userRepository.findById(masterId).get();

        List<Feedback> feedbackForMasterId = feedbackRepository.findAllByMasterId_Id(masterId);

//        feedbackRepository.saveAll(feedbackForMasterId);
        setProcedureFeedbackByLocale(feedbackForMasterId, locale);
        return feedbackForMasterId;
    }
}

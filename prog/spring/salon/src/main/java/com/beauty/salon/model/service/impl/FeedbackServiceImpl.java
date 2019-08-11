package com.beauty.salon.model.service.impl;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.repository.FeedbackRepository;
import com.beauty.salon.model.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void create(Feedback feedback) {
        feedbackRepository.saveAndFlush(feedback);
    }

//    @Override
//    public Feedback findFeedbackByRecordId(Integer recordId) {
//        return feedbackRepository.findFeedbackByRecordId_Id(recordId);
//    }

    @Override
    public List<Feedback> findFeedbacksByMasterId(Integer masterId) {
        return feedbackRepository.findAllByMasterId_Id(masterId);
    }
}

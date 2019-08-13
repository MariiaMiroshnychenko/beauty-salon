package com.beauty.salon.model.service.impl;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.repository.FeedbackRepository;
import com.beauty.salon.model.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Feedback> findFeedbacksByMasterId(Integer masterId, Pageable pageable) {
        return feedbackRepository.findAllByMasterId_Id(masterId, pageable);
    }
}

package com.beauty.salon.model.service.general.impl;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.repository.FeedbackRepository;
import com.beauty.salon.model.service.general.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

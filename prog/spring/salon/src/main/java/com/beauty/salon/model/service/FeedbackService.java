package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.User;

import java.util.List;

public interface FeedbackService {
    void create(Feedback feedback);
    Feedback findFeedbackByRecordId(Integer recordId);
    List<Feedback> findFeedbackByMasterId(Integer masterId);
}

package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedbackService {
    void create(Feedback feedback);
    Page<Feedback> findFeedbacksByMasterId(Integer masterId, Pageable pageable);
}

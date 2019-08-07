package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.Review;
import com.beauty.salon.model.entity.User;

import java.util.List;

public interface ReviewService {
    void create(Review review);
    Review findReviewByRecordId(Integer recordId);
    List<Review> findReviewsByMaster(User master);
}

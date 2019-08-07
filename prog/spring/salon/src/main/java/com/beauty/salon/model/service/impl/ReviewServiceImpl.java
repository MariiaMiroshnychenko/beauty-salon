package com.beauty.salon.model.service.impl;

import com.beauty.salon.model.entity.Review;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.repository.ReviewRepository;
import com.beauty.salon.model.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void create(Review review) {
        reviewRepository.saveAndFlush(review);
    }

    @Override
    public Review findReviewByRecordId(Integer recordId) {
        return reviewRepository.findReviewByRecordId_Id(recordId);
    }

    @Override
    public List<Review> findReviewsByMaster(User master) {
        return reviewRepository.findReviewsByMasterId(master);
    }
}

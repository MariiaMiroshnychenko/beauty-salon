package model.service;

import model.entity.Review;

import java.util.List;

public interface ReviewService {
    void create(Review review);
    Review findReviewByRecordId(Integer recordId);
    List<Review> findReviewByMasterId(Integer masterId);
}

package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.Review;
import com.beauty.salon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findReviewByRecordId_Id(Integer recordId);
    List<Review> findReviewsByMasterId(User master);
}

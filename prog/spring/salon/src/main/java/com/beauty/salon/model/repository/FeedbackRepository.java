package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
//    Feedback findFeedbackByRecordId_Id(Integer recordId);
    List<Feedback> findAllByMasterId_Id(Integer master);
}

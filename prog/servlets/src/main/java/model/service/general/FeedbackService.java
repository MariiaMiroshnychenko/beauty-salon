package model.service.general;

import model.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    void create(Feedback feedback);
    Feedback findReviewByRecordId(Integer recordId);
    List<Feedback> findReviewByMasterId(Integer masterId);
}

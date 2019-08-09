package model.dao;

import model.entity.Feedback;

import java.util.List;

public interface FeedbackDao extends GenericDao<Feedback> {
    Feedback findReviewByRecordId(Integer recordId);
    List<Feedback> findReviewsByMasterId(Integer masterId);
}

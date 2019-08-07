package model.dao;

import model.entity.Review;

import java.util.List;

public interface ReviewDao extends GenericDao<Review> {
    Review findReviewByRecordId(Integer recordId);
    List<Review> findReviewsByMasterId(Integer masterId);
}

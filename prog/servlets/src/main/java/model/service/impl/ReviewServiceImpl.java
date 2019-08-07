package model.service.impl;

import model.dao.FactoryDao;
import model.dao.ReviewDao;
import model.entity.Review;
import model.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    @Override
    public void create(Review review) {
        ReviewDao reviewDao = FactoryDao.getInstance().reviewDao();

        reviewDao.create(review);
        reviewDao.close();
    }

    @Override
    public Review findReviewByRecordId(Integer recordId) {
        ReviewDao reviewDao = FactoryDao.getInstance().reviewDao();
        Review review = reviewDao.findReviewByRecordId(recordId);

        reviewDao.close();
        return review;
    }

    @Override
    public List<Review> findReviewByMasterId(Integer masterId) {
        ReviewDao reviewDao = FactoryDao.getInstance().reviewDao();
        List<Review> reviews = reviewDao.findReviewsByMasterId(masterId);

        reviewDao.close();
        return reviews;
    }
}

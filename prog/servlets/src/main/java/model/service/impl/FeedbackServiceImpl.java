package model.service.impl;

import model.dao.FactoryDao;
import model.dao.FeedbackDao;
import model.entity.Feedback;
import model.service.FeedbackService;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    @Override
    public void create(Feedback feedback) {
        FeedbackDao feedbackDao = FactoryDao.getInstance().reviewDao();

        feedbackDao.create(feedback);
        feedbackDao.close();
    }

    @Override
    public Feedback findReviewByRecordId(Integer recordId) {
        FeedbackDao feedbackDao = FactoryDao.getInstance().reviewDao();
        Feedback feedback = feedbackDao.findReviewByRecordId(recordId);

        feedbackDao.close();
        return feedback;
    }

    @Override
    public List<Feedback> findReviewByMasterId(Integer masterId) {
        FeedbackDao feedbackDao = FactoryDao.getInstance().reviewDao();
        List<Feedback> feedbacks = feedbackDao.findReviewsByMasterId(masterId);

        feedbackDao.close();
        return feedbacks;
    }
}

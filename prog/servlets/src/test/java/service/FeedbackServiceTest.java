package service;

import model.service.FeedbackService;
import model.service.impl.FeedbackServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceTest {
    @Spy
    private FeedbackService feedbackService = new FeedbackServiceImpl();

    @Test
    public void findReviewByRecordIdTest() {
        assertEquals(LocalDateTime.of(2019, 8, 5, 19, 24, 23), feedbackService.findReviewByRecordId(1).getDateTime());
    }

    @Test
    public void findReviewByMasterIdTest() {
        assertEquals(2, feedbackService.findReviewByMasterId(2).size());
    }

    @Test (expected = NullPointerException.class)
    public void findReviewByMasterIdTestNull() {
        feedbackService.findReviewByMasterId(null);
    }

    @Test
    public void findReviewByMasterIdTestIncorrectInput() {
        assertEquals(0, feedbackService.findReviewByMasterId(-2).size());
    }
}

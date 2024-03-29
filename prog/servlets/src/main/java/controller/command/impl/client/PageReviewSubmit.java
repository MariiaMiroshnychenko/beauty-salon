package controller.command.impl.client;

import controller.command.Command;
import model.entity.Feedback;
import model.entity.User;
import model.service.general.FeedbackService;
import model.service.general.impl.FeedbackServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

public class PageReviewSubmit implements Command{
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        FeedbackService feedbackService = new FeedbackServiceImpl();

        feedbackService.create(new Feedback(user.getId(),
                Integer.valueOf(request.getParameter("masterId")),
                Integer.valueOf(request.getParameter("recordId")),
                request.getParameter("reviewText"),
                LocalDateTime.now()));

        return new ClientPageFeedback().execute(request);
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

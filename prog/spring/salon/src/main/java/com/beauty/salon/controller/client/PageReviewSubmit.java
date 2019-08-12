//package com.beauty.salon.controller.command.impl.client;
//
//import controller.command.Command;
//import model.entity.Feedback;
//import model.entity.User;
//import model.service.FeedbackService;
//import model.service.impl.FeedbackServiceImpl;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//public class PageReviewSubmit implements Command{
//    private String[] hasAuthority = {"client"};
//
//    @Override
//    public String execute(HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        FeedbackService reviewService = new FeedbackServiceImpl();
//
//        reviewService.create(new Feedback(user.getId(),
//                Integer.valueOf(request.getParameter("masterId")),
//                Integer.valueOf(request.getParameter("recordId")),
//                request.getParameter("reviewText"),
//                LocalDateTime.now()));
//
//        return new PageReview().execute(request);
//    }
//
//    @Override
//    public boolean checkAuthority(String role) {
//        return Arrays.asList(hasAuthority).contains(role);
//    }
//}

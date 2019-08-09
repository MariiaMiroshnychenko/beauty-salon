//package com.beauty.salon.controller.command.impl.client;
//
//import controller.command.Command;
//import model.entity.Record;
//import model.entity.User;
//import model.service.*;
//import model.service.impl.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
//public class PageReview implements Command {
//    private String[] hasAuthority = {"client"};
//
//    @Override
//    public String execute(HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        String language = request.getParameter("language");
//
//        RecordService recordService = new RecordServiceImpl();
//        UserService userService = new UserServiceImpl();
//        LanguageService languageService = new LanguageServiceImpl();
//        ProcedureService procedureService = new ProcedureServiceImpl();
//        ReviewService reviewService = new ReviewServiceImpl();
//
//        List<Record> records = recordService.findRecordsByUserId(user.getId(), user.getRole());
//
//        records.forEach(record -> record.setMaster(userService.findUserById(record.getMasterId())));
//
//        records.forEach(record -> record.setProcedure(procedureService.findProcedureByCodeAndLanguageId(
//                procedureService.findProcedureById(record.getProcedureId()).getCode(),
//                languageService.findLanguageByLocale(language).getId())));
//
//        records.forEach(record -> record.setClient(user));
//
//        List<Record> uncheckedRecords = new ArrayList<>();
//
//        records.forEach(record -> {
//                    if (Objects.isNull(reviewService.findReviewByRecordId(record.getId()))) {
//                        uncheckedRecords.add(record);
//                    }
//                }
//        );
//        request.setAttribute("recordsWithoutFeedback", uncheckedRecords);
//        return "/WEB-INF/view/client/review.jsp";
//    }
//
//    @Override
//    public boolean checkAuthority(String role) {
//        return Arrays.asList(hasAuthority).contains(role);
//    }
//}

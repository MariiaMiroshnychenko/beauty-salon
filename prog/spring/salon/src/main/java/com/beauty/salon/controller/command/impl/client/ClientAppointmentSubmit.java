//package com.beauty.salon.controller.command.impl.client;
//
//import controller.command.Command;
//import model.entity.Record;
//import model.entity.User;
//import model.service.RecordService;
//import model.service.impl.RecordServiceImpl;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Arrays;
//
//public class ClientAppointmentSubmit implements Command {
//    private String[] hasAuthority = {"client"};
//
//    @Override
//    public String execute(HttpServletRequest request) {
//        RecordService recordService = new RecordServiceImpl();
//        User client = (User) request.getSession().getAttribute("user");
//
//        recordService.create(new Record(Integer.valueOf(request.getParameter("masterId")),
//                Integer.valueOf(request.getParameter("procedure")),
//                LocalDate.parse(request.getParameter("date")),
//                LocalTime.parse(request.getParameter("time")),
//                client.getId()));
//
//        return "/WEB-INF/view/client/client-past-records.jsp";
//    }
//
//    @Override
//    public boolean checkAuthority(String role) {
//        return Arrays.asList(hasAuthority).contains(role);
//    }
//}

//package com.beauty.salon.controller.command.impl.client;
//
//import controller.command.Command;
//import model.entity.Record;
//import model.entity.User;
//import model.service.*;
//import model.service.impl.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Arrays;
//import java.util.List;
//
//public class ClientPastRecordPage implements Command {
//    private String[] hasAuthority = {"client"};
//
//    @Override
//    public String execute(HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        String language = request.getParameter("language");
//
//        RecordService recordService = new RecordServiceImpl();
//        ProcedureService procedureService = new ProcedureServiceImpl();
//        LanguageService languageService = new LanguageServiceImpl();
//        UserService userService = new UserServiceImpl();
//
//        List<Record> pastRecords = recordService.executeQuery(
//                "select * from record where client_id=? and record_date < ? or record_date = ? and `time` < ?",
//                user.getId(), LocalDate.now().toString(), LocalDate.now().toString(), LocalTime.now());
//
//        pastRecords.forEach(record -> record.setProcedure(
//                procedureService.findProcedureByCodeAndLanguageId(
//                        procedureService.findProcedureById(record.getProcedureId()).getCode(),
//                        languageService.findLanguageByLocale(language).getId())));
//
//        pastRecords.forEach(record -> record.setMaster(userService.findUserById(record.getMasterId())));
//
//        request.setAttribute("pastRecords", pastRecords);
//        return "/WEB-INF/view/client/client-past-records.jsp";
//    }
//
//    @Override
//    public boolean checkAuthority(String role) {
//        return Arrays.asList(hasAuthority).contains(role);
//    }
//}
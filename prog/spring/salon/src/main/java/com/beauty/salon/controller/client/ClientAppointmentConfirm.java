//package com.beauty.salon.controller.command.impl.client;
//
//import controller.command.Command;
//import model.entity.*;
//import model.service.*;
//import model.service.impl.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
//public class ClientAppointmentConfirm implements Command {
//    private String[] hasAuthority = {"client"};
//
//    @Override
//    public String execute(HttpServletRequest request) {
//        String date = request.getParameter("date");
//        String lang = request.getParameter("language");
//
//        RecordService recordService = new RecordServiceImpl();
//        LanguageService languageService = new LanguageServiceImpl();
//        ProcedureService procedureService = new ProcedureServiceImpl();
//
//        if (Objects.nonNull(date)) {
//            List<LocalTime> unavailableTimes = new ArrayList<>();
//            List<LocalTime> availableTimes = new ArrayList<>();
//
//            recordService.findRecordsByDateAndMasterId(date, Integer.valueOf(request.getParameter("masterId")))
//                    .forEach(record -> unavailableTimes.add(record.getTime()));
//
//            int beginHour = 10;
//            int minute = 0;
//            int endHour = 19;
//            LocalTime availableTime;
//
//            while (beginHour < endHour) {
//                availableTime = LocalTime.of(beginHour, minute);
//                if (unavailableTimes.contains(availableTime) || LocalDate.parse(date).equals(LocalDate.now()) && availableTime.compareTo(LocalTime.now()) < 0) {
//                    beginHour++;
//                    continue;
//                }
//
//                availableTimes.add(availableTime);
//                beginHour++;
//            }
//
//            Language language = languageService.findLanguageByLocale(lang);
//            List<Procedure> procedures = procedureService.findProceduresByLanguageId(language.getId());
//
//            request.getSession().setAttribute("availableTimes", availableTimes);
//            request.getSession().setAttribute("procedures", procedures);
//        }
//        return "/WEB-INF/view/client/make-appointment-confirm.ftl";
//    }
//
//    @Override
//    public boolean checkAuthority(String role) {
//        return Arrays.asList(hasAuthority).contains(role);
//    }
//}
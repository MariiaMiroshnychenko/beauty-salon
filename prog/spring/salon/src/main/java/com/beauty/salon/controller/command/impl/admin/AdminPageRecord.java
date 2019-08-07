package com.beauty.salon.controller.command.impl.admin;

import controller.command.Command;
import model.entity.Record;
import model.service.*;
import model.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.*;

public class AdminPageRecord implements Command {
    private String[] hasAuthority = {"admin"};

    @Override
    public String execute(HttpServletRequest request) {
        String date = request.getParameter("date");
        String language = request.getParameter("language");



        if(Objects.nonNull(date)) {
            RecordService recordService = new RecordServiceImpl();
            LanguageService languageService = new LanguageServiceImpl();
            ProcedureService procedureService = new ProcedureServiceImpl();
            UserService userService = new UserServiceImpl();
            ScheduleService scheduleService = new ScheduleServiceImpl();

            int beginHour = 10;
            int endHour = 18;
            int minute = 0;

            List<Record> recordsForDay;
            Map<LocalTime, List<Record>> scheduleForDateMap = new TreeMap<>();
            LocalTime time;

            while (beginHour <= endHour) {
                time = LocalTime.of(beginHour, minute);
                recordsForDay = recordService.findRecordsByDateAndTime(date, time);

                recordsForDay.forEach(record -> record.setMaster(userService.findUserById(record.getMasterId())));

                recordsForDay.forEach(record ->
                        record.setProcedure(procedureService.findProcedureByCodeAndLanguageId(
                                procedureService.findProcedureById(record.getProcedureId()).getCode(),
                                languageService.findLanguageByLocale(language).getId())));

                scheduleForDateMap.put(time, recordsForDay);
                beginHour++;
            }
                request.setAttribute("times", scheduleForDateMap.keySet());
                request.setAttribute("records", scheduleForDateMap.values());
                request.setAttribute("masters", scheduleService.findMastersByDay(date));
        }
        return "/WEB-INF/view/admin/adminPageMasters.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

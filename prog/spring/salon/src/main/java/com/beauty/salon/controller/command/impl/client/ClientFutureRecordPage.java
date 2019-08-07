package com.beauty.salon.controller.command.impl.client;

import controller.command.Command;
import model.entity.Record;
import model.entity.User;
import model.service.LanguageService;
import model.service.ProcedureService;
import model.service.RecordService;
import model.service.UserService;
import model.service.impl.LanguageServiceImpl;
import model.service.impl.ProcedureServiceImpl;
import model.service.impl.RecordServiceImpl;
import model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class ClientFutureRecordPage implements Command {
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String language = request.getParameter("language");

        RecordService recordService = new RecordServiceImpl();
        ProcedureService procedureService = new ProcedureServiceImpl();
        LanguageService languageService = new LanguageServiceImpl();
        UserService userService = new UserServiceImpl();

        List<Record> futureRecords = recordService.executeQuery(
                "select * from record where client_id=? and record_date > ? or record_date = ? and `time` > ?",
                user.getId(), LocalDate.now().toString(), LocalTime.now());

        futureRecords.forEach(record -> record.setProcedure(
                procedureService.findProcedureByCodeAndLanguageId(
                        procedureService.findProcedureById(record.getProcedureId()).getCode(),
                        languageService.findLanguageByLocale(language).getId())));

        futureRecords.forEach(record -> record.setMaster(userService.findUserById(record.getMasterId())));

        request.setAttribute("futureRecords", futureRecords);
        return "/WEB-INF/view/client/client-future-records.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}
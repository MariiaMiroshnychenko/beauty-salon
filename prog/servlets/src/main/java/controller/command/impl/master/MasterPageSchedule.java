package controller.command.impl.master;

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
import java.util.*;

public class MasterPageSchedule implements Command {
    private String[] hasAuthority = {"master"};

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String language = request.getParameter("language");
        String date = request.getParameter("date");

        RecordService recordService = new RecordServiceImpl();
        ProcedureService procedureService = new ProcedureServiceImpl();
        LanguageService languageService = new LanguageServiceImpl();
        UserService userService = new UserServiceImpl();

        List<Record> records = recordService.findRecordsByDateAndMasterId(LocalDate.parse(date), user.getId());

        records.forEach(record -> {
            record.setProcedure(
                    procedureService.findProcedureByCodeAndLanguageId(
                            procedureService.findProcedureById(record.getProcedureId()).getCode(),
                            languageService.findLanguageByLocale(language).getId()));

            record.setClient(userService.findUserById(record.getClientId()));
        });

        Map<LocalTime, Record> recordMap = new TreeMap<>();

        records.forEach(record -> recordMap.put(record.getTime(), record));

        int beginHour = 10;
        int endHour = 18;
        int minute = 0;

        while (beginHour <= endHour) {
            recordMap.putIfAbsent(LocalTime.of(beginHour, minute), null);
            beginHour++;
        }

        request.setAttribute("times", recordMap.keySet());
        request.setAttribute("masterRecords", recordMap.values());

        return "/WEB-INF/view/master/masterSchedule.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

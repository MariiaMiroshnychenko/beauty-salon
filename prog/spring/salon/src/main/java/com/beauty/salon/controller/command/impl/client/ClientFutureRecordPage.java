package com.beauty.salon.controller.command.impl.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/client")
public class ClientFutureRecordPage {

    @GetMapping("/client-future-records")
    public String execute(HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        String language = request.getParameter("language");
//
//        RecordService recordService = new RecordServiceImpl();
//        ProcedureService procedureService = new ProcedureServiceImpl();
//        LanguageService languageService = new LanguageServiceImpl();
//        UserService userService = new UserServiceImpl();
//
//        List<Record> futureRecords = recordService.executeQuery(
//                "select * from record where client_id=? and record_date > ? or record_date = ? and `time` > ?",
//                user.getId(), LocalDate.now().toString(), LocalTime.now());
//
//        futureRecords.forEach(record -> record.setProcedure(
//                procedureService.findProcedureByCodeAndLanguageId(
//                        procedureService.findProcedureById(record.getProcedureId()).getCode(),
//                        languageService.findLanguageByLocale(language).getId())));
//
//        futureRecords.forEach(record -> record.setMaster(userService.findUserById(record.getMasterId())));
//
//        request.setAttribute("futureRecords", futureRecords);
        return "client-future-records";
    }
}
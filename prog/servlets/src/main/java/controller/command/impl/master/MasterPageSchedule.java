package controller.command.impl.master;

import controller.command.Command;
import model.entity.Record;
import model.entity.User;
import model.service.user.MasterService;
import model.service.user.impl.MasterServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

public class MasterPageSchedule implements Command {
    private String[] hasAuthority = {"master"};

    @Override
    public String execute(HttpServletRequest request) {
        MasterService masterService = new MasterServiceImpl();

        User user = (User) request.getSession().getAttribute("user");
        String language = request.getParameter("language");
        String date = request.getParameter("date");

        Map<LocalTime, Record> recordMap = masterService.masterScheduleMap(date, user, language);

        request.setAttribute("times", recordMap.keySet());
        request.setAttribute("masterRecords", recordMap.values());

        return "/WEB-INF/view/master/masterSchedule.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

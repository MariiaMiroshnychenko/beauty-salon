package controller.command.impl.admin;

import controller.command.Command;
import model.entity.Record;
import model.service.general.ScheduleService;
import model.service.general.impl.ScheduleServiceImpl;
import model.service.user.AdminService;
import model.service.user.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdminPageRecord implements Command {
    private String[] hasAuthority = {"admin"};

    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = new AdminServiceImpl();
        ScheduleService scheduleService = new ScheduleServiceImpl();

        String date = request.getParameter("date");
        String language = request.getParameter("language");

        if (Objects.nonNull(date)) {
            Map<LocalTime, List<Record>> scheduleForDateMap =
                    adminService.sortedRecordsByTime(LocalDate.parse(date), language);

            request.setAttribute("times", scheduleForDateMap.keySet());
            request.setAttribute("records", scheduleForDateMap.values());
            request.setAttribute("masters", scheduleService.findMastersByDay(LocalDate.parse(date)));
        }
        return "/WEB-INF/view/admin/adminPageMasters.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

package controller.command.impl.client;

import controller.command.Command;
import model.entity.Schedule;
import model.entity.User;
import model.service.ScheduleService;
import model.service.UserService;
import model.service.impl.ScheduleServiceImpl;
import model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientAppointmentDate implements Command {
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        int lastVisitHour = 18;
        int minute = 0;

        String date = request.getParameter("visitDate");

        ScheduleService scheduleService = new ScheduleServiceImpl();
        UserService userService = new UserServiceImpl();

        if (LocalDate.parse(date).compareTo(LocalDate.now()) < 0 || LocalDate.parse(date).equals(LocalDate.now()) && LocalTime.now().compareTo(LocalTime.of(lastVisitHour, minute)) >= 0) {
            request.setAttribute("dateError", "date.error");
            return new ClientAppointment().execute(request);
        }
            List<Schedule> schedules = scheduleService.findMastersByDay(date);
            List<User> masters = new ArrayList<>();

            schedules.forEach(schedule -> masters.add(userService.findUserById(schedule.getMasterId())));

            request.setAttribute("masterList", masters);

        return new ClientAppointment().execute(request);
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

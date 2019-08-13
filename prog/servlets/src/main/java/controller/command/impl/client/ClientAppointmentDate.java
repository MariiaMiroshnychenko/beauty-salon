package controller.command.impl.client;

import container.ConstantWorkHour;
import controller.command.Command;
import model.service.user.ClientService;
import model.service.user.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class ClientAppointmentDate implements Command {
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        ClientService clientService = new ClientServiceImpl();

        int lastVisitHour = ConstantWorkHour.END_HOUR;
        int minute = ConstantWorkHour.MINUTE;

        String date = request.getParameter("visitDate");

        if (LocalDate.parse(date).compareTo(LocalDate.now()) < 0 ||
                LocalDate.parse(date).equals(LocalDate.now()) &&
                        LocalTime.now().compareTo(LocalTime.of(lastVisitHour, minute)) >= 0) {

            request.setAttribute("dateError", "date.error");
            return new ClientAppointment().execute(request);
        }

        request.setAttribute("masterList", clientService.masterList(LocalDate.parse(date)));

        return new ClientAppointment().execute(request);
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

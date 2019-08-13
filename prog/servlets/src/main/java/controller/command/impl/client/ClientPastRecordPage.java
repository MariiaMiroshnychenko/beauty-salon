package controller.command.impl.client;

import container.Query;
import controller.command.Command;
import model.entity.Record;
import model.entity.User;
import model.service.*;
import model.service.impl.*;
import model.service.user.ClientService;
import model.service.user.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class ClientPastRecordPage implements Command {
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        ClientService clientService = new ClientServiceImpl();

        User user = (User) request.getSession().getAttribute("user");
        String language = request.getParameter("language");

        request.setAttribute("pastRecords", clientService.futureOrPastRecords(user, language, Query.PAST_RECORDS));

        return "/WEB-INF/view/client/client-past-records.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}
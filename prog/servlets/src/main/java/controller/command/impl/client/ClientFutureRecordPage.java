package controller.command.impl.client;

import container.PagePath;
import container.Query;
import controller.command.Command;
import model.entity.User;
import model.service.user.ClientService;
import model.service.user.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

public class ClientFutureRecordPage implements Command {
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String language = Objects.nonNull(request.getParameter("language")) ?
                request.getParameter("language") :
                request.getSession().getAttribute("language").toString();

        ClientService clientService = new ClientServiceImpl();

        request.setAttribute("futureRecords", clientService.futureOrPastRecords(user, language, Query.FUTURE_RECORDS));

        return PagePath.PAGE_CLIENT_FUTURE_RECORD;
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}
package controller.command.impl.client;

import controller.command.Command;
import model.entity.Record;
import model.entity.User;
import model.service.*;
import model.service.impl.*;
import model.service.user.ClientService;
import model.service.user.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class PageReview implements Command {
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        ClientService clientService = new ClientServiceImpl();

        User user = (User) request.getSession().getAttribute("user");
        String language = request.getParameter("language");

        request.setAttribute("recordsWithoutFeedback", clientService.uncheckedRecords(user, language));

        return "/WEB-INF/view/client/review.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

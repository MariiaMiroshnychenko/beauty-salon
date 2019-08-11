package controller.command.impl.admin;

import controller.command.Command;
import model.service.*;
import model.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class AdminPageFeedback implements Command {
    private String[] hasAuthority = {"admin"};

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();

        request.getSession().setAttribute("masters", userService.findUsersByRole("master"));

        return "/WEB-INF/view/admin/admin-feedback.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}
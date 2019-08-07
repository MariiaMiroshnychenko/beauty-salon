package controller.command.impl;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;

public class Authorization implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/authorization.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return true;
    }
}

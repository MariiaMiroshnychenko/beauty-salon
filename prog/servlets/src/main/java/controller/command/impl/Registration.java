package controller.command.impl;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/registration.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return true;
    }
}

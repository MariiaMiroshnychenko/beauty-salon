package controller.command.impl;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class Logout implements Command {
    private String[] hasAuthority = {"admin", "client", "master"};
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/authorization.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

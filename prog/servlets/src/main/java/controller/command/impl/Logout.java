package controller.command.impl;

import container.PagePath;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class Logout implements Command {
    private String[] hasAuthority = {"admin", "client", "master"};
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PAGE_AUTHORIZATION;
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

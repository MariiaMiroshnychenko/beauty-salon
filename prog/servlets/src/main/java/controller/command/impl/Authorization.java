package controller.command.impl;

import container.PagePath;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class Authorization implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PAGE_AUTHORIZATION;
    }

    @Override
    public boolean checkAuthority(String role) {
        return true;
    }
}

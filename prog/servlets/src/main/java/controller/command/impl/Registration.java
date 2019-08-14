package controller.command.impl;

import container.PagePath;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PAGE_REGISTRATION;
    }

    @Override
    public boolean checkAuthority(String role) {
        return true;
    }
}

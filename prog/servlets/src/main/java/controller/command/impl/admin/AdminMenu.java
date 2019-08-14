package controller.command.impl.admin;

import container.PagePath;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class AdminMenu implements Command {
    private String[] hasAuthority = {"admin"};

    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PAGE_ADMIN_MENU;
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

package controller.command.impl.client;

import container.PagePath;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class ClientAppointment implements Command {
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PAGE_CLIENT_MAKE_APPOINTMENT;
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}
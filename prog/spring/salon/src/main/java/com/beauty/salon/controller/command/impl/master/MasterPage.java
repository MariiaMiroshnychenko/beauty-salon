package com.beauty.salon.controller.command.impl.master;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class MasterPage implements Command {
    private String[] hasAuthority = {"master"};
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/master/masterSchedule.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

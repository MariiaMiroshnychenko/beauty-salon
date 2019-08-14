package controller.command.impl.master;

import container.PagePath;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class MasterPage implements Command {
    private String[] hasAuthority = {"master"};
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PAGE_MASTER_SCHEDULE;
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

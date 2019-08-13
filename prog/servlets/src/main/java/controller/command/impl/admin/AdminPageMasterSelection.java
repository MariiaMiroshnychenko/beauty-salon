package controller.command.impl.admin;

import controller.command.Command;
import model.service.general.UserService;
import model.service.general.impl.UserServiceImpl;
import model.service.user.AdminService;
import model.service.user.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

public class AdminPageMasterSelection implements Command {
    private String[] hasAuthority = {"admin"};

    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = new AdminServiceImpl();
        UserService userService = new UserServiceImpl();

        Integer masterId = Integer.valueOf(request.getParameter("master"));
        String language = Objects.nonNull(request.getParameter("language")) ?
                request.getParameter("language") :
                request.getSession().getAttribute("language").toString();

        request.setAttribute("reviewsForMaster", adminService.feedbackForMaster(masterId, language));
        request.getSession().setAttribute("masters", userService.findUsersByRole("master"));

        return "/WEB-INF/view/admin/admin-feedback.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

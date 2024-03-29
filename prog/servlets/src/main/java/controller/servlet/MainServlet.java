package controller.servlet;

import controller.command.Command;
import controller.command.impl.Authorization;
import controller.command.impl.Logout;
import controller.command.impl.Registration;
import controller.command.impl.admin.AdminPageFeedback;
import controller.command.impl.admin.AdminPageMasterSelection;
import controller.command.impl.admin.AdminPageRecord;
import controller.command.impl.client.*;
import controller.command.impl.master.MasterPageSchedule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private Map<String, Command> commands;

    @Override
    public void init() throws ServletException {
        commands = new HashMap<>();
        commands.put("registration", new Registration());
        commands.put("authorization", new Authorization());
        commands.put("logout", new Logout());
        commands.put("client-past-records", new ClientPastRecordPage());
        commands.put("client-future-records", new ClientFutureRecordPage());
        commands.put("make-appointment", new ClientAppointment());
        commands.put("appointment-for-date", new ClientAppointmentDate());
        commands.put("feedback", new ClientPageFeedback());
        commands.put("submit", new PageReviewSubmit());
        commands.put("make-appointment-confirm", new ClientAppointmentConfirm());
        commands.put("make-appointment-success", new ClientAppointmentSubmit());
        commands.put("master-records", new AdminPageRecord());
        commands.put("master-feedback", new AdminPageFeedback());
        commands.put("master-submit", new AdminPageMasterSelection());
        commands.put("schedule", new MasterPageSchedule());
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF8");

        String path = req.getRequestURI();

        path = path.replaceAll(".*/", "");
        path = path.replaceAll("\\?*", "");

        Command command = commands.getOrDefault(path, commands.get("authorization"));

        String page;
        String role = (String) req.getSession().getAttribute("role");

        if (command.checkAuthority(role)) {
            page = command.execute(req);
        } else {
            page = "/WEB-INF/view/error.jsp";
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
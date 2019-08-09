//package com.beauty.salon.controller.command.impl.client;
//
//import controller.command.Command;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//
//public class ClientAppointment implements Command {
//    private String[] hasAuthority = {"client"};
//
//    @Override
//    public String execute(HttpServletRequest request) {
//        return "/WEB-INF/view/client/make-appointment.jsp";
//    }
//
//    @Override
//    public boolean checkAuthority(String role) {
//        return Arrays.asList(hasAuthority).contains(role);
//    }
//}
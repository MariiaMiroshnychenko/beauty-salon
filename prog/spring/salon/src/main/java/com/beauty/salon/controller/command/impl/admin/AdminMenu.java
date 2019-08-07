package com.beauty.salon.controller.command.impl.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMenu {

    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/admin/admin-menu.jsp";
    }
}

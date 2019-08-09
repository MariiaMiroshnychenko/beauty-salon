package com.beauty.salon.controller.admin;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.service.FeedbackService;
import com.beauty.salon.model.service.ProcedureService;
import com.beauty.salon.model.service.UserService;
import com.beauty.salon.model.service.admin.AdminService;
import com.beauty.salon.model.service.admin.impl.AdminServiceImpl;
import com.beauty.salon.model.service.impl.FeedbackServiceImpl;
import com.beauty.salon.model.service.impl.ProcedureServiceImpl;
import com.beauty.salon.model.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private AdminService adminService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl,
                           AdminServiceImpl adminServiceImpl) {
        this.userService = userServiceImpl;
        this.adminService = adminServiceImpl;
    }

    @GetMapping("/menu")
    public String getAdminMenuPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        model.addAttribute("admin", user);
        return "/admin/admin-menu";
    }

    @GetMapping("/master-feedback")
    public String getMastersFeedbackPage(@RequestParam(name = "reviewsForMaster", required = false)
                                                     List<Feedback> reviewsForMaster, Model model) {

        model.addAttribute("masters", userService.findUsersByRole("master"));
        model.addAttribute("reviewsForMaster", reviewsForMaster);

        return "/admin/admin-feedback";
    }

    @PostMapping("/master-submit")
    public String submitMasterForFeedbackPage(Integer master, String lang,
                                              Model model) {
        return getMastersFeedbackPage(adminService.findFeedbackMasterById(master, lang), model);
    }
}

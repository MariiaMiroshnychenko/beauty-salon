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
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private FeedbackService feedbackService;
    private AdminService adminService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl,
                           FeedbackServiceImpl feedbackServiceImpl,
                           AdminServiceImpl adminServiceImpl) {
        this.userService = userServiceImpl;
        this.feedbackService = feedbackServiceImpl;
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
    public String getMastersFeedbackPage(
            @RequestParam(name = "reviewsForMaster", required = false) List<Feedback> reviewsForMaster,
                                         Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        model.addAttribute("admin", user);
        model.addAttribute("masters", userService.findUsersByRole("master"));
        model.addAttribute("reviewsForMaster", reviewsForMaster != null);

        return "/admin/admin-feedback";
    }

    @GetMapping("/master-feedback/master-submit")
    public String submitMasterForFeedbackPage(Integer master, String lang, Model model) {
//        String lang
//        System.out.println(master);
//        System.out.println(lang);
        return getMastersFeedbackPage(adminService.findFeedbacksByMasterId(master, lang), model);
//        User user = userService.findUserById(master);
//
//        model.addAttribute("reviewsForMaster", feedbackService.findFeedbacksByMaster(user));
//        return "/admin/admin-feedback";
//        return getMastersFeedbackPage(model);
    }
}
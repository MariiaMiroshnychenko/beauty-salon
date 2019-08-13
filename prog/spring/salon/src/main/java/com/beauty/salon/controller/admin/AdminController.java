package com.beauty.salon.controller.admin;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.service.ScheduleService;
import com.beauty.salon.model.service.UserService;
import com.beauty.salon.model.service.admin.AdminService;
import com.beauty.salon.model.service.admin.impl.AdminServiceImpl;
import com.beauty.salon.model.service.impl.ScheduleServiceImpl;
import com.beauty.salon.model.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private AdminService adminService;
    private ScheduleService scheduleService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl,
                           AdminServiceImpl adminServiceImpl,
                           ScheduleServiceImpl scheduleServiceImpl) {
        this.userService = userServiceImpl;
        this.adminService = adminServiceImpl;
        this.scheduleService = scheduleServiceImpl;
    }

    @GetMapping("/menu")
    public String getAdminMenuPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        model.addAttribute("admin", user);
        return "page/admin/admin-menu";
    }

    @GetMapping("/master-feedback")
    public String getMastersFeedbackPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        model.addAttribute("reviewsForMasterNonNull", false);
        model.addAttribute("admin", user);
        model.addAttribute("masters", userService.findUsersByRole("master"));

        return "page/admin/admin-feedback";
    }

    @GetMapping("/master-feedback/submit")
    public String submitMasterForFeedbackPage(@RequestParam(name = "reviewsForMaster", required = false) List<Feedback> reviewsForMaster,
                                              @RequestParam(name = "master") Integer master,
                                              @RequestParam(name = "lang") String lang,
                                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                              Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Page<Feedback> masterFeedback = adminService.findFeedbacksByMasterId(master, lang, pageable);

        model.addAttribute("admin", user);
        model.addAttribute("masters", userService.findUsersByRole("master"));
        model.addAttribute("master", master);
        model.addAttribute("reviewsForMasterNonNull", masterFeedback != null);
        model.addAttribute("reviewsForMaster", masterFeedback);
        model.addAttribute("page", masterFeedback);

        return "page/admin/admin-feedback";
    }

    @GetMapping("/salon-schedule")
    public String getMastersRecords(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        model.addAttribute("selectedDate", LocalDate.now());
        model.addAttribute("admin", user);
        model.addAttribute("dateNonNull", false);

        return "page/admin/adminPageSchedule";
    }

    @GetMapping("/salon-schedule/date-selected")
    public String selectDateForSchedule(@RequestParam(name = "date", required = false) LocalDate date,
                                        @RequestParam(name = "lang", required = false) String lang,
                                        Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Map<LocalTime, List<Record>> masterRecordsMap = adminService.sortedRecordsByTime(date, lang);

        masterRecordsMap.values().forEach(g -> g.forEach(h -> System.out.println(h.getProcedureId().getName())));

        model.addAttribute("selectedDate", date);
        model.addAttribute("admin", user);
        model.addAttribute("dateNonNull", Objects.nonNull(masterRecordsMap));
        model.addAttribute("times", masterRecordsMap.keySet());
        model.addAttribute("records", masterRecordsMap.values());
        model.addAttribute("masters", scheduleService.findMastersByDay(date));

        return "page/admin/adminPageSchedule";
    }
}
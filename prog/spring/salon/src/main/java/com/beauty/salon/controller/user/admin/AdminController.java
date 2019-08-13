package com.beauty.salon.controller.user.admin;

import com.beauty.salon.container.PagePath;
import com.beauty.salon.controller.user.MainController;
import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.service.general.ScheduleService;
import com.beauty.salon.model.service.general.UserService;
import com.beauty.salon.model.service.user.AdminService;
import com.beauty.salon.model.service.user.impl.AdminServiceImpl;
import com.beauty.salon.model.service.general.impl.ScheduleServiceImpl;
import com.beauty.salon.model.service.general.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
public class AdminController implements MainController {
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
        model.addAttribute("admin", getAuthorizedUser());
        return PagePath.PAGE_ADMIN_MENU;
    }

    @GetMapping("/master-feedback")
    public String getMastersFeedbackPage(Model model) {
        model.addAttribute("admin", getAuthorizedUser());
        model.addAttribute("reviewsForMasterNonNull", false);
        model.addAttribute("masters", userService.findUsersByRole("master"));

        return PagePath.PAGE_ADMIN_FEEDBACK;
    }

    @GetMapping("/master-feedback/submit")
    public String submitMasterForFeedbackPage(@RequestParam(name = "reviewsForMaster", required = false) List<Feedback> reviewsForMaster,
                                              @RequestParam(name = "master") Integer master,
                                              @RequestParam(name = "lang") String lang,
                                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                              Model model) {
        Page<Feedback> masterFeedback = adminService.findFeedbacksByMasterId(master, lang, pageable);

        model.addAttribute("admin", getAuthorizedUser());
        model.addAttribute("masters", userService.findUsersByRole("master"));
        model.addAttribute("master", master);
        model.addAttribute("reviewsForMasterNonNull", Objects.nonNull(masterFeedback));
        model.addAttribute("reviewsForMaster", masterFeedback);

        return PagePath.PAGE_ADMIN_FEEDBACK;
    }

    @GetMapping("/salon-schedule")
    public String getMastersRecords(Model model) {
        LocalDate date = LocalDate.now();

        model.addAttribute("admin", getAuthorizedUser());
        model.addAttribute("selectedDate", date);
        model.addAttribute("dateNonNull", false);

        return PagePath.PAGE_ADMIN_SCHEDULE;
    }

    @GetMapping("/salon-schedule/date-selected")
    public String selectDateForSchedule(@RequestParam(name = "date", required = false) LocalDate date,
                                        @RequestParam(name = "lang", required = false) String lang,
                                        Model model) {
        Map<LocalTime, List<Record>> masterRecordsMap = adminService.sortedRecordsByTime(date, lang);

        model.addAttribute("admin", getAuthorizedUser());
        model.addAttribute("selectedDate", date);
        model.addAttribute("dateNonNull", Objects.nonNull(masterRecordsMap));
        model.addAttribute("times", masterRecordsMap.keySet());
        model.addAttribute("records", masterRecordsMap.values());
        model.addAttribute("masters", scheduleService.findMastersByDay(date));

        return PagePath.PAGE_ADMIN_SCHEDULE;
    }
}
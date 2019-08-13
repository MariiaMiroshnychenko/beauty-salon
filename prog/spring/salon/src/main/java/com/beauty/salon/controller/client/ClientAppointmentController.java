package com.beauty.salon.controller.client;

import com.beauty.salon.controller.MainController;
import com.beauty.salon.model.service.general.EmailService;
import com.beauty.salon.model.service.general.ScheduleService;
import com.beauty.salon.model.service.general.impl.EmailServiceImpl;
import com.beauty.salon.model.service.general.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/client/make-appointment")
public class ClientAppointmentController implements MainController {
    private EmailService emailService;
    private ScheduleService scheduleService;

    @Autowired
    public ClientAppointmentController(EmailServiceImpl emailServiceImpl,
                                       ScheduleServiceImpl scheduleServiceImpl) {
        this.emailService = emailServiceImpl;
        this.scheduleService = scheduleServiceImpl;
    }

    @GetMapping
    public String getAppointmentPage(Model model) {
        model.addAttribute("client", getAuthorizedUser());
        model.addAttribute("notificationsAmount", emailService.clientEmailAmount(getAuthorizedUser()));
        model.addAttribute("selectedDate", LocalDate.now());
        model.addAttribute("masterListNonNull", false);
        return "/page/client/make-appointment";
    }

    @GetMapping("/select-date")
    public String selectDateForAppointment(@RequestParam(name = "date", required = false) String date,
                                           Model model) {
        LocalDate requestDate = LocalDate.parse(date);

        model.addAttribute("client", getAuthorizedUser());
        model.addAttribute("notificationsAmount", emailService.clientEmailAmount(getAuthorizedUser()));
        model.addAttribute("selectedDate", date);

        if (requestDate.compareTo(LocalDate.now()) < 0 || requestDate.equals(LocalDate.now())
                && LocalTime.now().compareTo(LocalTime.of(18, 0)) >= 0) {
            model.addAttribute("dateError", "date.error");
        }

        model.addAttribute("masterListNonNull", true);
        model.addAttribute("masterList", scheduleService.findMastersByDay(requestDate));
        return "/page/client/make-appointment";
    }
}

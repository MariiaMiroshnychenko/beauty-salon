package com.beauty.salon.controller.client;

import com.beauty.salon.model.entity.Email;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.service.EmailService;
import com.beauty.salon.model.service.ProcedureService;
import com.beauty.salon.model.service.RecordService;
import com.beauty.salon.model.service.ScheduleService;
import com.beauty.salon.model.service.client.ClientService;
import com.beauty.salon.model.service.client.impl.ClientServiceImpl;
import com.beauty.salon.model.service.impl.EmailServiceImpl;
import com.beauty.salon.model.service.impl.ProcedureServiceImpl;
import com.beauty.salon.model.service.impl.RecordServiceImpl;
import com.beauty.salon.model.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    private EmailService emailService;
    private ScheduleService scheduleService;
    private ClientService clientService;
    private ProcedureService procedureService;
    private RecordService recordService;

    @Autowired
    public ClientController(EmailServiceImpl emailServiceImpl,
                            ScheduleServiceImpl scheduleServiceImpl,
                            ClientServiceImpl clientServiceImpl,
                            ProcedureServiceImpl procedureServiceImpl,
                            RecordServiceImpl recordServiceImpl) {
        this.emailService = emailServiceImpl;
        this.scheduleService = scheduleServiceImpl;
        this.clientService = clientServiceImpl;
        this.procedureService = procedureServiceImpl;
        this.recordService = recordServiceImpl;
    }

    private User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping("/notification")
    public String getNotification(Model model) {
        List<Email> clientEmails = emailService.findEmailsByClient(getAuthorizedUser());

        model.addAttribute("client", getAuthorizedUser());
        model.addAttribute("notificationsAmount", clientEmails.size());
        model.addAttribute("notifications", clientEmails);

        return "page/client/client-notification";
    }

    @GetMapping("/make-appointment")
    public String getAppointmentPage(Model model) {
        model.addAttribute("client", getAuthorizedUser());
        model.addAttribute("notificationsAmount", emailService.clientEmailAmount(getAuthorizedUser()));
        model.addAttribute("selectedDate", LocalDate.now());
        model.addAttribute("masterListNonNull", false);
        return "/page/client/make-appointment";
    }

    @GetMapping("/make-appointment/select-date")
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

    @GetMapping("/make-appointment/select-date/send-confirmation")
    public String selectMaster(@RequestParam(name = "date") String date,
                               @RequestParam(name = "masterId") Integer masterId,
                               @RequestParam(name = "masterFullName") String masterFullName,
                               @RequestParam(name = "lang") String lang,
                               Model model) {

        model.addAttribute("client", getAuthorizedUser());
        model.addAttribute("date", date);
        model.addAttribute("masterId", masterId);
        model.addAttribute("masterFullName", masterFullName);
        model.addAttribute("lang", lang);
        model.addAttribute("availableTimes", clientService.availableTimes(date, masterId, 10, 18, 0));
        model.addAttribute("procedures", procedureService.findProceduresByLocale(lang));

        return "page/client/make-appointment-confirm";
    }

    @PostMapping("/make-appointment/select-date/send-confirmation")
    public String submitAppointment(Integer masterId, Integer procedure,
                                    String date, String time) {
        Record record = Record.builder()
                ;
        return "redirect:/notification";
    }
}

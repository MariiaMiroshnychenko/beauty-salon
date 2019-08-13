package com.beauty.salon.controller.user.client;

import com.beauty.salon.container.ConstantWorkHour;
import com.beauty.salon.container.PagePath;
import com.beauty.salon.controller.user.MainController;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.service.general.EmailService;
import com.beauty.salon.model.service.general.ProcedureService;
import com.beauty.salon.model.service.general.RecordService;
import com.beauty.salon.model.service.general.UserService;
import com.beauty.salon.model.service.general.impl.EmailServiceImpl;
import com.beauty.salon.model.service.general.impl.ProcedureServiceImpl;
import com.beauty.salon.model.service.general.impl.RecordServiceImpl;
import com.beauty.salon.model.service.general.impl.UserServiceImpl;
import com.beauty.salon.model.service.user.ClientService;
import com.beauty.salon.model.service.user.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("client/make-appointment/select-date/send-confirmation")
public class ClientAppointmentConfirmationController implements MainController {
    private EmailService emailService;
    private ClientService clientService;
    private ProcedureService procedureService;
    private RecordService recordService;
    private UserService userService;

    @Autowired
    public ClientAppointmentConfirmationController(EmailServiceImpl emailServiceImpl,
                                                   ClientServiceImpl clientServiceImpl,
                                                   ProcedureServiceImpl procedureServiceImpl,
                                                   RecordServiceImpl recordServiceImpl,
                                                   UserServiceImpl userServiceImpl) {
        this.emailService = emailServiceImpl;
        this.clientService = clientServiceImpl;
        this.procedureService = procedureServiceImpl;
        this.recordService = recordServiceImpl;
        this.userService = userServiceImpl;
    }

    @GetMapping
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
        model.addAttribute("availableTimes", clientService.availableTimes(LocalDate.parse(date), masterId,
                ConstantWorkHour.BEGIN_HOUR, ConstantWorkHour.END_HOUR, ConstantWorkHour.MINUTE));
        model.addAttribute("procedures", procedureService.findProceduresByLocale(lang));

        return PagePath.PAGE_CLIENT_APPOINTMENT_CONFIRM;
    }

    @PostMapping
    public String submitAppointment(Integer masterId, Integer procedure,
                                    String date, String time, String lang) {
        Record record = Record.builder()
                .masterId(userService.findUserById(masterId))
                .procedureId(procedureService.findProcedureById(procedure))
                .recordDate(LocalDate.parse(date))
                .time(LocalTime.parse(time))
                .clientId(getAuthorizedUser())
                .build();
        recordService.create(record);

        emailService.createEmail(record, lang);

        return PagePath.REDIRECT_TO_CLIENT_NOTIFICATION_PAGE;
    }
}

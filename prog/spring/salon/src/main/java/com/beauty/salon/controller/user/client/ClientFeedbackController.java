package com.beauty.salon.controller.user.client;

import com.beauty.salon.container.PagePath;
import com.beauty.salon.controller.user.MainController;
import com.beauty.salon.model.entity.Email;
import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.service.general.EmailService;
import com.beauty.salon.model.service.general.FeedbackService;
import com.beauty.salon.model.service.general.RecordService;
import com.beauty.salon.model.service.general.UserService;
import com.beauty.salon.model.service.general.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/client")
public class ClientFeedbackController implements MainController {
    private EmailService emailService;
    private FeedbackService feedbackService;
    private UserService userService;
    private RecordService recordService;

    @Autowired
    public ClientFeedbackController(EmailServiceImpl emailServiceImpl,
                                    FeedbackServiceImpl feedbackServiceImpl,
                                    UserServiceImpl userServiceImpl,
                                    RecordServiceImpl recordServiceImpl) {
        this.emailService = emailServiceImpl;
        this.feedbackService = feedbackServiceImpl;
        this.userService = userServiceImpl;
        this.recordService = recordServiceImpl;
    }

    @GetMapping("/notification")
    public String getNotification(Model model, Pageable pageable) {
        Page<Email> clientEmails = emailService.findEmailsByClient(getAuthorizedUser(), pageable);

        model.addAttribute("client", getAuthorizedUser());
        model.addAttribute("notificationsAmount", emailService.clientEmailAmount(getAuthorizedUser()));
        model.addAttribute("notifications", clientEmails);

        return PagePath.PAGE_CLIENT_NOTIFICATION;
    }

    @GetMapping("/feedback")
    public String getFeedbackPage(String masterName, String masterSurname,
                                  String procedure, Integer record,
                                  Integer master,  Integer email, Model model) {

        model.addAttribute("client", getAuthorizedUser());
        model.addAttribute("notificationsAmount", emailService.clientEmailAmount(getAuthorizedUser()));
        model.addAttribute("masterName", masterName);
        model.addAttribute("masterSurname", masterSurname);
        model.addAttribute("procedure", procedure);
        model.addAttribute("record", record);
        model.addAttribute("master", master);
        model.addAttribute("email", email);

        return PagePath.PAGE_CLIENT_REVIEW;
    }

    @PostMapping("/feedback/submit")
    public String submitFeedback(Integer master, Integer record, String feedbackText, Integer email) {
        Feedback feedback = Feedback.builder()
                                        .clientId(getAuthorizedUser())
                                        .masterId(userService.findUserById(master))
                                        .text(feedbackText)
                                        .dateTime(LocalDateTime.now())
                                        .recordId(recordService.findRecordById(record))
                                    .build();

        feedbackService.create(feedback);
        emailService.updateEmailStatus(email);

        return PagePath.REDIRECT_TO_CLIENT_NOTIFICATION_PAGE;
    }
}

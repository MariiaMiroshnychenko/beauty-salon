package controller.command.impl.admin;

import controller.command.Command;
import model.entity.Feedback;
import model.service.*;
import model.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AdminPageMasterSelection implements Command {
    private String[] hasAuthority = {"admin"};

    @Override
    public String execute(HttpServletRequest request) {
        Integer masterId = Integer.valueOf(request.getParameter("master"));
        String language = Objects.nonNull(request.getParameter("language")) ?
                request.getParameter("language") :
                request.getSession().getAttribute("language").toString();

        FeedbackService feedbackService = new FeedbackServiceImpl();
        RecordService recordService = new RecordServiceImpl();
        LanguageService languageService = new LanguageServiceImpl();
        ProcedureService procedureService = new ProcedureServiceImpl();
        UserService userService = new UserServiceImpl();

        List<Feedback> reviewsForMasterId = feedbackService.findReviewByMasterId(masterId);

        reviewsForMasterId.forEach(review -> {
            review.setClient(userService.findUserById(review.getClientId()));
            review.setMaster(userService.findUserById(review.getMasterId()));
            review.setRecord(recordService.findRecordById(review.getRecordId()));
        });

        reviewsForMasterId.forEach(review ->
                review.getRecord().setProcedure(procedureService.findProcedureByCodeAndLanguageId(
                        procedureService.findProcedureById(review.getRecord().getProcedureId()).getCode(),
                        languageService.findLanguageByLocale(language).getId())
                ));

        request.setAttribute("reviewsForMaster", reviewsForMasterId);
        request.getSession().setAttribute("masters", userService.findUsersByRole("master"));

        return "/WEB-INF/view/admin/admin-feedback.jsp";
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}

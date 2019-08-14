package controller.command.impl.client;

import container.ConstantWorkHour;
import container.PagePath;
import controller.command.Command;
import model.entity.Language;
import model.entity.Procedure;
import model.service.general.LanguageService;
import model.service.general.ProcedureService;
import model.service.general.impl.LanguageServiceImpl;
import model.service.general.impl.ProcedureServiceImpl;
import model.service.user.ClientService;
import model.service.user.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClientAppointmentConfirm implements Command {
    private String[] hasAuthority = {"client"};

    @Override
    public String execute(HttpServletRequest request) {
        ClientService clientService = new ClientServiceImpl();
        LanguageService languageService = new LanguageServiceImpl();
        ProcedureService procedureService = new ProcedureServiceImpl();

        String date = request.getParameter("date");
        String lang = request.getParameter("language");

        if (Objects.nonNull(date)) {
            Language language = languageService.findLanguageByLocale(lang);
            List<Procedure> procedures = procedureService.findProceduresByLanguageId(language.getId());

            request.getSession().setAttribute("availableTimes", clientService.availableTime(
                    LocalDate.parse(date), request.getParameter("masterId"),
                    ConstantWorkHour.BEGIN_HOUR, ConstantWorkHour.END_HOUR, ConstantWorkHour.MINUTE
            ));

            request.getSession().setAttribute("procedures", procedures);
        }
        return PagePath.PAGE_CLIENT_APPOINTMENT_CONFIRM;
    }

    @Override
    public boolean checkAuthority(String role) {
        return Arrays.asList(hasAuthority).contains(role);
    }
}
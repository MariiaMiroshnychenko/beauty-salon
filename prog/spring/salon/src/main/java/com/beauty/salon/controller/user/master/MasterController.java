package com.beauty.salon.controller.user.master;

import com.beauty.salon.container.ConstantWorkHour;
import com.beauty.salon.container.PagePath;
import com.beauty.salon.controller.user.MainController;
import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.service.user.MasterService;
import com.beauty.salon.model.service.user.impl.MasterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Controller
@RequestMapping("/master")
public class MasterController implements MainController {
    private MasterService masterService;

    @Autowired
    public MasterController(MasterServiceImpl masterServiceImpl) {
        this.masterService = masterServiceImpl;
    }

    @GetMapping("/schedule")
    public String getMasterSchedulePage(Model model) {
        model.addAttribute("master", getAuthorizedUser());
        model.addAttribute("dateNonNull", false);
        model.addAttribute("selectedDate", LocalDate.now());

        return PagePath.PAGE_MASTER_SCHEDULE;
    }

    @GetMapping("/schedule/records-date")
    public String getMasterScheduleForDay(@RequestParam(name = "date", required = false) LocalDate date,
                                          @RequestParam(name = "lang") String lang,
                                          Model model) {
        Map<LocalTime, Record> masterScheduleMap =
                masterService.masterSchedule(date, getAuthorizedUser().getId(), lang);

        model.addAttribute("master", getAuthorizedUser());
        model.addAttribute("selectedDate", date);
        model.addAttribute("dateNonNull", true);
        model.addAttribute("times", masterScheduleMap.keySet());
        model.addAttribute("masterRecords", masterScheduleMap.values());
        model.addAttribute("recordMessage", "message.no.records");

        return PagePath.PAGE_MASTER_SCHEDULE;
    }
}

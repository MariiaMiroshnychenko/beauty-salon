package com.beauty.salon.controller.master;

import com.beauty.salon.model.entity.Record;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.service.master.MasterService;
import com.beauty.salon.model.service.master.impl.MasterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/master")
public class MasterController {
    private MasterService masterService;

    @Autowired
    public MasterController(MasterServiceImpl masterServiceImpl) {
        this.masterService = masterServiceImpl;
    }

    @GetMapping("/schedule")
    public String getMasterSchedulePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        model.addAttribute("master", user);
        model.addAttribute("dateNonNull", false);
        model.addAttribute("selectedDate", LocalDate.now());

        return "page/master/masterSchedule";
    }

    @GetMapping("/schedule/records-date")
    public String getMasterScheduleForDay(@RequestParam(name = "date", required = false) String date,
                                          @RequestParam(name = "lang") String lang,
                                          Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Map<LocalTime, Record> masterScheduleMap =
                masterService.masterSchedule(date, user.getId(), lang, 10, 18, 0);

        model.addAttribute("master", user);
        model.addAttribute("selectedDate", date);
        model.addAttribute("dateNonNull", true);
        model.addAttribute("times", masterScheduleMap.keySet());
        model.addAttribute("masterRecords", masterScheduleMap.values());
        model.addAttribute("recordMessage", "message.no.records");

        return "page/master/masterSchedule";
    }
}

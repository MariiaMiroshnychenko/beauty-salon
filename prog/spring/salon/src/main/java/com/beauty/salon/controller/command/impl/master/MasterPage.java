package com.beauty.salon.controller.command.impl.master;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping("/master")
public class MasterPage {
    private String[] hasAuthority = {"master"};

    @GetMapping
    public String getMasterPage() {
        return "masterSchedule";
    }
}

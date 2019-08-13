package com.beauty.salon.controller;

import com.beauty.salon.container.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthorizationController {
    @GetMapping
    public String getAuthorizationPage() {
        return PagePath.PAGE_AUTHORIZATION;
    }
}

package com.beauty.salon.controller;

import com.beauty.salon.container.PagePath;
import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.service.general.UserService;
import com.beauty.salon.model.service.general.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationController(UserServiceImpl userServiceImpl,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String getRegistrationPage(@RequestParam(value = "usernameError", required = false)
                                              String usernameError, Model model) {
        model.addAttribute("usernameError", usernameError != null);
        return PagePath.PAGE_REGISTRATION;
    }

    @PostMapping("/register")
    public String registerUser(String name, String surname,
                               String email, String usernameReg,
                               String repeatedPassword, Model model) {

        if (Objects.nonNull(userService.loadUserByUsername(usernameReg))) {
            return getRegistrationPage("username.error", model);
        } else {
            userService.create(User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .role("client")
                    .username(usernameReg)
                    .password(bCryptPasswordEncoder.encode(repeatedPassword))
                    .build());
            return PagePath.REDIRECT_TO_LOGIN_PAGE;
        }
    }
}

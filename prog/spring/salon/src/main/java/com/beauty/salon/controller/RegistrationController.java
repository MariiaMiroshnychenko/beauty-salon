package com.beauty.salon.controller;

import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserServiceImpl userServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationController(UserServiceImpl userServiceImpl,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String getRegistrationPage(@RequestParam(value = "usernameError", required = false)
                                              String usernameError, Model model) {
        model.addAttribute("usernameError", usernameError != null);
        return "page/registration";
    }

    @PostMapping("/register")
    public String registerUser(String name, String surname,
                               String email, String usernameReg,
                               String repeatedPassword, Model model) {

        if (Objects.nonNull(userServiceImpl.loadUserByUsername(usernameReg))) {
            return getRegistrationPage("username.error", model);

        } else {
            userServiceImpl.create(User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .role("client")
                    .username(usernameReg)
                    .password(bCryptPasswordEncoder.encode(repeatedPassword))
                    .build());
            return "redirect:/login";
        }
    }
}

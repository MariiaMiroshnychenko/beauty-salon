package com.beauty.salon.controller.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class Authorization {

    private MessageSource messageSource;

    @Autowired
    public Authorization(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public String getAuthorizationPage() {


        public String greeting() {
            /**
             *   @LocaleContextHolder.getLocale()
             *  Return the Locale associated with the given user context,if any, or the system default Locale otherwise.
             *  This is effectively a replacement for Locale.getDefault(), able to optionally respect a user-level Locale setting.
             */
            return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
        }
        return "authorization";
    }
}

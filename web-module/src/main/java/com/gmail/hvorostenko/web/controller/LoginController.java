package com.gmail.hvorostenko.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/denied-page")
    public String getFailPage(Model model) {
        return "denied";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }


}

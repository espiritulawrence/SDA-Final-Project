package com.sda.online_store_final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/home")
    public String login() {
        return "home";
    }

    @RequestMapping(value = "/admin")
    public String adminLogin() {
        return "adminHome";
    }

    @RequestMapping(value = "faqs")
    public String faqs() {
        return "faqs";
    }

    @RequestMapping(value = "contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping(value = "about")
    public String about() {
        return "about";
    }


}

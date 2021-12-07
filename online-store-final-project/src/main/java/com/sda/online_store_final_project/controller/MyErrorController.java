package com.sda.online_store_final_project.controller;

import com.sda.online_store_final_project.repository.MyErrorInterface;
import org.springframework.stereotype.Controller;


@Controller
public class MyErrorController implements MyErrorInterface {

    private final static String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {

        return ERROR_PATH;
    }
}

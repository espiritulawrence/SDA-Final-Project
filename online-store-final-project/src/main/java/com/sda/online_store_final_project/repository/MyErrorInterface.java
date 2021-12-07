package com.sda.online_store_final_project.repository;

import org.springframework.boot.web.servlet.error.ErrorController;

public interface MyErrorInterface extends ErrorController {
    String getErrorPath();
}

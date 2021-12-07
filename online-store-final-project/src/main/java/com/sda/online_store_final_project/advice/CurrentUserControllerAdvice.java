package com.sda.online_store_final_project.advice;

import com.sda.online_store_final_project.entity.UserAccount;
import com.sda.online_store_final_project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
@DependsOn("passwordEncoder")
public class CurrentUserControllerAdvice {

    @Autowired
    UserAccountService userAccountService;

    @ModelAttribute("currentUser")
    public UserAccount getCurrentUser(Authentication authentication) {
        if(authentication == null) {
            return null;
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserAccount userAccount = userAccountService.findOne(userDetails.getUsername());// Email as username
            return userAccount;
        }
    }

}

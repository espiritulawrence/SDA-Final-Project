package com.sda.online_store_final_project.controller;

import com.sda.online_store_final_project.entity.UserAccount;
import com.sda.online_store_final_project.repository.UserAccountRepository;
import com.sda.online_store_final_project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserAccountRepository userAccountRepository;


    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/")
    public String Handler(Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
            return "forward:" + "/home";
        } else {
            return "forward:" + "/admin";
        }

    }

    @GetMapping("/register")
    public String showForm(Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);

        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userAccount") UserAccount userAccount, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("userAccount", userAccount);
        UserAccount userWithEnteredEmailExists = userAccountService.findOne(userAccount.getEmail());
        if (userWithEnteredEmailExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAccount", userAccount);
            return "register";
        }
        userAccountService.save(userAccount);
        return "redirect:" + "/login";
    }

    // in case there is profile page
//    @GetMapping("/profiles")
//    public String showUser(UserAccount userAccount) {
//
//        return "/user/show";
//    }
//
//    @PostMapping("/profiles")
//    public String editUser(@Valid @ModelAttribute("user") UserAccount userAccount, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal, Model model) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("userAccount", userAccount);
//            return "/user/show";
//        }
//        //Access deny
//        if (!principal.getName().equals(userAccount.getEmail())) {
//            return "redirect:" + "/403";
//        }
//        userAccountService.update(userAccount);
//        model.addAttribute("msg", "Profile is updated!");
//        model.addAttribute("url", "/profiles");
//        return "common/success";
//    }

    @GetMapping("/403")
    public String accessDeny(Model model) {
        model.addAttribute("msg", "Access denied!");
        return "/common/error";
    }
    @GetMapping("/404")
    public String notFound(Model model) {
        model.addAttribute("msg", "Page not found!");
        return "/common/error";
    }

}

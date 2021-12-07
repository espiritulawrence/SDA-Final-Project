package com.sda.online_store_final_project.controller;

import com.sda.online_store_final_project.dto.Item;
import com.sda.online_store_final_project.entity.UserAccount;
import com.sda.online_store_final_project.form.ItemForm;
import com.sda.online_store_final_project.service.CartService;
import com.sda.online_store_final_project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collection;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("")
    public String findAll(Model model){
        Collection<Item> items = cartService.findAll();
        BigDecimal total = cartService.getTotal();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "/cart";
    }

    @PostMapping("")
    public String addToCart(@Valid ItemForm itemForm, BindingResult bindingResult, Model model) {

        cartService.addItem(itemForm);
        return "redirect:" + "/cart";
    }

    @PostMapping("/checkout")
    public  String checkout(Model model, Principal principal) {
        UserAccount userAccount = userAccountService.findOne(principal.getName());// Email as username
        cartService.checkout(userAccount);

        model.addAttribute("url", "/order");
        return "/common/success";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("product_id") String productId) {
        cartService.removeItem(productId);
        return "redirect:" + "/cart";
    }

    @GetMapping("/change")
    public String changeQuantity(@RequestParam("product_id") String productId, @RequestParam("quantity") Integer quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:" + "/cart";
    }
}

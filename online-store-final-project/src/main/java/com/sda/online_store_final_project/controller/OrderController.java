package com.sda.online_store_final_project.controller;

import com.sda.online_store_final_project.entity.OrderLine;
import com.sda.online_store_final_project.entity.OrderMain;
import com.sda.online_store_final_project.service.OrderService;
import com.sda.online_store_final_project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/order")
    public String OrderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                            Authentication authentication,
                            Model model) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<OrderMain> orderList;
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
            orderList = orderService.findByCustomerEmail(authentication.getName(), request);
        } else {
            orderList = orderService.findAll(request);
        }


        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("statusArray", new String[] {"New","Finished" ,"Canceled" });
        model.addAttribute("orders", orderList);
        return "/order";
    }


    @GetMapping("/order/cancel/{id}")
    public String cancel(@PathVariable("id") Long orderId, Model model, Authentication authentication){
        OrderMain orderMain = orderService.findOne(orderId);
        if(authentication.getName()!= orderMain.getCustomerEmail() && authentication.getAuthorities().contains("ROLE_CUSTOMER") ){
            return "redirect:" + "/403";
        }
        orderService.cancel(orderId);
        return "redirect:" + "/order";
    }

    @GetMapping("/order/finish/{id}")
    public String finish(@PathVariable("id") Long orderId, Model model){
        orderService.finish(orderId);
        return "redirect:" + "/order";
    }

    @GetMapping("/order/show/{id}")
    public String show(@PathVariable("id") Long orderId, Model model){

        OrderMain orderMain = orderService.findOne(orderId);


        Collection<OrderLine> items = orderMain.getProducts();
        model.addAttribute("items", items);
        return "/show-order";
    }
}

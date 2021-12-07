package com.sda.online_store_final_project.service;

import com.sda.online_store_final_project.dto.Item;
import com.sda.online_store_final_project.entity.UserAccount;
import com.sda.online_store_final_project.form.ItemForm;

import java.math.BigDecimal;
import java.util.Collection;


public interface CartService {
    void addItem(ItemForm itemForm);
    void removeItem(String productId);
    void updateQuantity(String productId, Integer quantity);

    Collection<Item> findAll();

    void  checkout(UserAccount userAccount);

    BigDecimal getTotal();

}

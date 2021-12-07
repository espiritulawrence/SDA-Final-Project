package com.sda.online_store_final_project.dto;

import com.sda.online_store_final_project.entity.Product;
import lombok.Data;


@Data
public class Item {
    private Product product;

    private Integer quantity;

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

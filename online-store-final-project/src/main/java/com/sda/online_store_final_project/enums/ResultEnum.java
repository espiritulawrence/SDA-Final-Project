package com.sda.online_store_final_project.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "Parameter Error!"),
    PRODUCT_NOT_EXIST(10, "Product does not exist!"),
    PRODUCT_NOT_ENOUGH(11, "Not enough products in stock!"),
    PRODUCT_STATUS_ERROR(12, "Status is incorrect!"),
    PRODUCT_OFF_SALE(13,"Product is off sale!"),
    PRODUCT_NOT_IN_CART(14,"Product is not in the cart!"),

    CART_CHECKOUT_SUCCESS(20, "Checkout successfully!"),

    CATEGORY_NOT_FOUND(30, "Category does not exist!"),
    SUBCATEGORY_NOT_FOUND(40, "SubCategory does not exist!"),

    ORDER_NOT_FOUND(50, "Order does not exist!"),
    ORDER_STATUS_ERROR(51, "Status is not correct"),


    VALID_ERROR(60, "Wrong information"),
    USER_NOT_FOUND(70,"User is not found!");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
package com.sda.online_store_final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name="PRODUCT_PRICE")
    private BigDecimal productPrice;

    @Column(name = "CATEGORY_TYPE")
    private Integer categoryType;

    @Column(name = "PRODUCT_STOCK")
    private Integer productStock;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(name = "PRODUCT_IMAGE")
    private String productImage;


    @Column(name = "PRODUCT_STATUS")
    private Integer productStatus;

}

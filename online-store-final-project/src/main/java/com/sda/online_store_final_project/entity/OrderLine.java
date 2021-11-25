package com.sda.online_store_final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private OrderMain orderMain;

    @NotEmpty
    @Column(name = "PRODUCT_ID")
    private String productId;

    @NotEmpty
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @NotNull
    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(name = "PRODUCT_IMAGE")
    private String productImage;

    @NotNull
    @Column(name = "PRODUCT_TYPE")
    private Integer categoryType;

    @NotNull
    @Column(name = "PRODUCT_PRICE")
    private BigDecimal productPrice;

    @Min(1)
    @Column(name = "QUANTITY")
    private Integer productQuantity;

    public OrderLine(Product product, Integer quantity) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productImage = product.getProductImage();
        this.categoryType = product.getCategoryType();
        this.productPrice = product.getProductPrice();
        this.productQuantity = quantity;
    }

    // fix bi-direction toString() recursion problem
    @Override
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productImage + '\'' +
                ", categoryType=" + categoryType +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderLine that = (OrderLine) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(productImage, that.productImage) &&
                Objects.equals(categoryType, that.categoryType) &&
                Objects.equals(productPrice, that.productPrice) &&
                Objects.equals(productQuantity, that.productQuantity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, productId, productName, productDescription, productImage, categoryType, productPrice, productQuantity);
    }
}

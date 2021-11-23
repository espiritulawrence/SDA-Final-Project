package com.sda.online_store_final_project.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data

public class OrderMain {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "orderMain")
    private Set<OrderLine> products = new HashSet<>();

    @NotEmpty
    private String customerEmail;

    @NotEmpty
    private String customerName;

    @NotEmpty
    private String customerPhone;

    @NotEmpty
    private String customerAddress;

    // Total Amount
    @NotNull
    private BigDecimal orderAmount;

    @NotNull
    @ColumnDefault("0")
    private Integer orderStatus;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    public OrderMain(){

    }

    public OrderMain(UserAccount customer) {
        this.customerEmail = customer.getEmail();
        this.customerName = customer.getName();
        this.customerPhone = customer.getPhone();
        this.customerAddress = customer.getAddress();
        this.orderStatus = 0;
    }
}

package com.sda.online_store_final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderMain {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "orderMain")
    private Set<OrderLine> products = new HashSet<>();

    @NotEmpty
    @Column(name = "CUSTOMER_EMAIL")
    private String customerEmail;

    @NotEmpty
    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @NotEmpty
    @Column(name = "CUSTOMER_PHONE")
    private String customerPhone;

    @NotEmpty
    @Column(name = "CUSTOMER_ADDRESS")
    private String customerAddress;

    // Total Amount
    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal orderAmount;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "ORDER_STATUS")
    private Integer orderStatus;

    @CreationTimestamp
    @Column(name = "ORDER_DATE")
    private LocalDateTime createTime;

    public OrderMain(UserAccount customer) {
        this.customerEmail = customer.getEmail();
        this.customerName = customer.getName();
        this.customerPhone = customer.getPhone();
        this.customerAddress = customer.getAddress();
        this.orderStatus = 0;
    }
}

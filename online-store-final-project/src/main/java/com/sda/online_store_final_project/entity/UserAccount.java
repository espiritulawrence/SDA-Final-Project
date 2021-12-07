package com.sda.online_store_final_project.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Data
@Table(name = "accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @NaturalId
    @NotEmpty
    @Column(name = "USER_EMAIL")
    private String email;

    @NotEmpty
    @Column(name = "USER_PASSWORD")
    @Size(min=3, message = "Length must be more than 3")
    private String password;

    @NotEmpty
    @Column(name = "USER_NAME")
    private String name;

    @NotEmpty
    @Column(name = "PHONE")
    private String phone;

    @NotEmpty
    @Column(name = "ADDRESS")
    private String address;

    @NotEmpty
    @Column(name = "ACCOUNT_ROLE")
    private String role;


}

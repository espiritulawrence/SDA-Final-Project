package com.sda.online_store_final_project.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
@Table(name = "accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min=3, message = "Length must be more than 3")
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String address;
    @NotNull
    private boolean active;
    @NotEmpty
    private String role;


}

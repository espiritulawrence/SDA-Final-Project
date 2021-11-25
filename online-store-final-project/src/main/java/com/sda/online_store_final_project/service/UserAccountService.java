package com.sda.online_store_final_project.service;

import com.sda.online_store_final_project.entity.UserAccount;

import java.util.Collection;


public interface UserAccountService {
    UserAccount findOne(String email);
    Collection<UserAccount> findByRole(String role);
    void save(UserAccount userAccount);
    void update(UserAccount userAccount);
}

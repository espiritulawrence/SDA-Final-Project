package com.sda.online_store_final_project.repository;

import com.sda.online_store_final_project.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    UserAccount findByEmail(String email);
    Collection<UserAccount> findAllByRole(String role);

}

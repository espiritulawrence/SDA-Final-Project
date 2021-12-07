package com.sda.online_store_final_project.service.impl;


import com.sda.online_store_final_project.entity.UserAccount;
import com.sda.online_store_final_project.enums.ResultEnum;
import com.sda.online_store_final_project.exception.MyException;
import com.sda.online_store_final_project.repository.UserAccountRepository;
import com.sda.online_store_final_project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
@DependsOn("passwordEncoder")
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserAccountRepository userAccountRepository;
    @Override
    public UserAccount findOne(String email) {
        return userAccountRepository.findByEmail(email);
    }

    @Override
    public Collection<UserAccount> findByRole(String role) {
        return userAccountRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public void save(UserAccount userAccount) {
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        try {
            userAccountRepository.save(userAccount);
        } catch (Exception e) {
            throw new MyException(ResultEnum.VALID_ERROR);
        }
    }

    @Override
    public void update(UserAccount userAccount) {
        UserAccount oldUser = userAccountRepository.findByEmail(userAccount.getEmail());
        oldUser.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        oldUser.setName(userAccount.getName());
        oldUser.setPhone(userAccount.getPhone());
        oldUser.setAddress(userAccount.getAddress());
        userAccountRepository.save(oldUser);
    }

}

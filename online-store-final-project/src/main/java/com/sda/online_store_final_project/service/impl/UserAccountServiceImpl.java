package com.sda.online_store_final_project.service.impl;


import com.sda.online_store_final_project.entity.UserAccount;
import com.sda.online_store_final_project.repository.UserAccountRepository;
import com.sda.online_store_final_project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
public class UserAccountServiceImpl implements UserAccountService {

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
        userAccount.setPassword(userAccount.getPassword());
            userAccountRepository.save(userAccount);

    }

    @Override
    public void update(UserAccount userAccount) {
        UserAccount oldUser = userAccountRepository.findByEmail(userAccount.getEmail());
        oldUser.setPassword(userAccount.getPassword());
        oldUser.setName(userAccount.getName());
        oldUser.setPhone(userAccount.getPhone());
        oldUser.setAddress(userAccount.getAddress());
        userAccountRepository.save(oldUser);
    }

}

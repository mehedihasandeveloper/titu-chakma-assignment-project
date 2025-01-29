package com.mehedihasandev.inventoryCRUD.service;

import com.mehedihasandev.inventoryCRUD.model.User;
import com.mehedihasandev.inventoryCRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
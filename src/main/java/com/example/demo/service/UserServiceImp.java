package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User registration(@RequestBody User user){
        return null;
    }
}

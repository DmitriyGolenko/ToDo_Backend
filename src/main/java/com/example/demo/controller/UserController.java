package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserServiceImp userService;

    @GetMapping("/registration")
    User registration(@RequestBody User user){
        return userService.registration(user);
    }

}

package com.app.TODO_backend.controller;


import com.app.TODO_backend.entity.User;
import com.app.TODO_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/registration")
    User registration(@RequestBody User user){
        return null;
    }

}

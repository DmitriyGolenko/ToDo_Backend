package com.app.TODO_backend.controller;

import com.app.TODO_backend.service.EmailService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Hidden
public class HelloController {
    private final EmailService emailService;

    @GetMapping("/unsecured")
    public String unsecured(){
        emailService.sendMailMessage("tt7545946@gmail.com","test message","This is test message");
        return "unsecured";
    }
    @GetMapping("/secured")
    public String sayHello(){
        return "secured";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/current")
    public String currentUser(Principal principal){
        return principal.getName();
    }

}

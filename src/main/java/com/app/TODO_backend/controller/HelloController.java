package com.app.TODO_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {
    @GetMapping("/unsecured")
    public String unsecured(){
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

package com.Springboot.CRUD.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.CRUD.DTO.LoginRequest;
import com.Springboot.CRUD.Service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestHeader("Email") String email, @RequestHeader("Password") String password) {
        System.out.println("Email Header = " + email);
        System.out.println("Password Header = " + password);

        System.out.println("LOGIN API HIT");
    

    return authService.login(email, password);
    }
    
}



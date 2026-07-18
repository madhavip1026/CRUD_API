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

    @PostMapping("/auth/login")
public String login(
        @RequestBody LoginRequest request) {
            System.out.println("LOGIN API HIT");

    return authService.login(
        request.getEmail(),
        request.getPassword()
    );
}

    
    
}



package com.Springboot.CRUD.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.CRUD.DTO.LoginRequest;
import com.Springboot.CRUD.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestHeader("email") String email,
            @RequestHeader("password") String password) {

        return ResponseEntity.ok(
                authService.login(email, password)
        );
    }
}



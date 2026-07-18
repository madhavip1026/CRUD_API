package com.Springboot.CRUD.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/profile")
    public Map<String, Object> profile(Authentication authentication) {

        Map<String, Object> info = new HashMap<>();

        info.put("email", authentication.getPrincipal());
        info.put("authorities", authentication.getAuthorities());

        return info;
    }
}
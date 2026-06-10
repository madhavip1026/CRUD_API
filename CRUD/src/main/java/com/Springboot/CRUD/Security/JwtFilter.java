package com.Springboot.CRUD.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Springboot.CRUD.Service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header =
                request.getHeader("Authorization");

        System.out.println("Authorization Header: " + header);
        System.out.println("Request URI: " + request.getRequestURI());

        if(header != null &&
           header.startsWith("Bearer ")) {

            String token =
                    header.substring(7);
            
            System.out.println("Token: " + token);

            if(jwtService.validateToken(token)) {

                System.out.println(
                        "JWT Token Valid");
                
                // Set authentication context with proper authorities
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                "user", null, java.util.Arrays.asList(
                                        new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("Authentication set in SecurityContext");
            } else {
                System.out.println("JWT Token Invalid");
            }
        } else {
            System.out.println("No Bearer token found");
        }

        filterChain.doFilter(
                request,
                response);
    }
}

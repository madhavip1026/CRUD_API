package com.Springboot.CRUD.Security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Springboot.CRUD.Entity.Employee;
import com.Springboot.CRUD.Repository.EmployeeRepository;
import com.Springboot.CRUD.Service.JWTService;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User user =
                (OAuth2User) authentication.getPrincipal();

        String email =
                user.getAttribute("email");

        Employee employee =
                employeeRepository.findByEmail(email)
                        .orElse(null);

        String role = (employee != null) ? employee.getRole() : "USER";

        String jwt =
                jwtService.generateToken(email, role);

        response.getWriter().write(jwt);
    }
}

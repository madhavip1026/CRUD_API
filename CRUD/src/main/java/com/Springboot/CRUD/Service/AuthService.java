package com.Springboot.CRUD.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Springboot.CRUD.Entity.Employee;
import com.Springboot.CRUD.Repository.EmployeeRepository;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) {

        Employee employee = repository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!password.equals(employee.getPassword())) {
    throw new RuntimeException("Invalid credentials");
}

        return jwtService.generateToken(
                employee.getEmail(),
                employee.getRole());
    }

}


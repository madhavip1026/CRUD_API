package com.Springboot.CRUD.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Springboot.CRUD.DTO.LoginRequest;
import com.Springboot.CRUD.Entity.Employee;
import com.Springboot.CRUD.Repository.EmployeeRepository;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private JWTService jwtService;

    
    public String login(LoginRequest request) {

    System.out.println("Inside AuthService");

    Employee employee =
            repository.findByEmail(request.getEmail())
            .orElseThrow(() ->
                    new RuntimeException("User Not Found"));

    System.out.println("Employee Found");

    String token =
            jwtService.generateToken(employee.getEmail());

    System.out.println("Generated Token = " + token);

    return token;
}
}


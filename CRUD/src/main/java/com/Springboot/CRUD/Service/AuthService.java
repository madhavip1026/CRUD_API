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

    
    public String login(String email,
        String password) {

    System.out.println("Inside AuthService");

    Employee employee =
            repository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User Not Found"));
        System.out.println("Employee Found");
    if (!employee.getPassword()
            .equals(password)) {

        throw new RuntimeException("Invalid Password");
    }

    return jwtService.generateToken(
            employee.getEmail(),
            employee.getRole());

    //String token =
           // jwtService.generateToken(employee.getEmail(), employee.getRole());

    //System.out.println("Generated Token = " + token);

    //return token;
}
}


package com.Springboot.CRUD.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.CRUD.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

}

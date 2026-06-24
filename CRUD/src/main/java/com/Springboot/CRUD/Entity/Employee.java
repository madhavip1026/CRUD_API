package com.Springboot.CRUD.Entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @JsonProperty("additionalInfo")
   @JdbcTypeCode(SqlTypes.JSON)
   @Column(name = "additional_info", columnDefinition = "json")
    private Map<String, Object> additionalInfo;

    private String name;
    private String email;
    private String password;
    private String department;
    private String role;   
    
    // No-arg constructor for JPA and Jackson
    //public Employee() {
   //}
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    //public Employee(Long id, Map<String, Object> additionalInfo, String name, String email, String department) {
      //  this.id = id;
        //this.additionalInfo = additionalInfo;
        //this.name = name;
        //this.email = email;
        //this.department = department;
    //}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }
    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}


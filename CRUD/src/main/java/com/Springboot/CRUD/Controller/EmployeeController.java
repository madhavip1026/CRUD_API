package com.Springboot.CRUD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.CRUD.Entity.Employee;
import com.Springboot.CRUD.Service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
   EmployeeService service;

   @GetMapping
   public List<Employee> getEmployees() {
      return service.getAllEmployees();
   }
   @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {

        return service.getEmployee(id);
    }
   @PostMapping
public Employee createEmployee(@RequestBody Employee employee) {
    try {
        System.out.println("Received = " + employee.getAdditionalInfo());
        return service.createEmployee(employee);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error creating employee: " + e.getMessage(), e);
    }
}
   //@PostMapping
//public Employee createEmployee(
        //@RequestBody Employee employee) {

    //return service.createEmployee(employee);
   //}
   @PutMapping("/{id}")
public Employee updateEmployee(
        @PathVariable Long id,
        @RequestBody Employee employee) {

    return service.updateEmployee(id, employee);
}
@DeleteMapping("/{id}")
public String deleteEmployee(
        @PathVariable Long id) {

    service.deleteEmployee(id);

    return "Deleted Successfully";
}

}

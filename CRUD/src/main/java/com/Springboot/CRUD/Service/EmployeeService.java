package com.Springboot.CRUD.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.Springboot.CRUD.Entity.Employee;
import com.Springboot.CRUD.Repository.EmployeeRepository;

@Service
public class EmployeeService {
   @Autowired
   EmployeeRepository repository;
   @Autowired
   private EmployeeCacheService cacheService;

   public Employee createEmployee(Employee employee) {
      return repository.save(employee);
   }
   
   public List<Employee> getAllEmployees() {
      return repository.findAll();
   }
    public Employee getEmployee(Long id) {

    Employee cachedEmployee = cacheService.get(id);

    if (cachedEmployee != null) {

        System.out.println("CACHE HIT");

        return cachedEmployee;
    }

    System.out.println("CACHE MISS");

    System.out.println("Fetching from Database");

    Employee employee = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Employee Not Found"));

    cacheService.save(employee);

    return employee;
}
    

    // Update
   public Employee updateEmployee(Long id,
                               Employee request) {

    Employee employee = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Employee not found"));

    employee.setName(request.getName());
    employee.setEmail(request.getEmail());
    employee.setDepartment(request.getDepartment());
    employee.setAdditionalInfo(request.getAdditionalInfo());

    Employee updatedEmployee =
            repository.save(employee);

    cacheService.save(updatedEmployee);

    System.out.println("Cache Updated");

    return updatedEmployee;
}
    // Delete
   public void deleteEmployee(Long id) {

    Employee employee = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Employee not found"));

    repository.delete(employee);

    cacheService.delete(id);
}

}

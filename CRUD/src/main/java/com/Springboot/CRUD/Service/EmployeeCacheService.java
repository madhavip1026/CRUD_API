
package com.Springboot.CRUD.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.Springboot.CRUD.Entity.Employee;

@Service
public class EmployeeCacheService {

    private final Map<Long, Employee> cache =
            new ConcurrentHashMap<>();

    public Employee get(Long id) {

        System.out.println("Checking Cache for Employee: " + id);

        return cache.get(id);
    }

    public void save(Employee employee) {

        System.out.println("Saving Employee into Cache: "
                + employee.getId());

        cache.put(employee.getId(), employee);
    }

    public void delete(Long id) {

        System.out.println("Removing Employee from Cache: "
                + id);

        cache.remove(id);
    }
}


package com.trujillo.employee.management.domain.port;

import com.trujillo.employee.management.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
    List<Employee> saveAll(List<Employee> employees);
    void deleteById(Long id);
    boolean existsById(Long id);
}

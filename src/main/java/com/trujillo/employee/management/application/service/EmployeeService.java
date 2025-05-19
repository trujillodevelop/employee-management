package com.trujillo.employee.management.application.service;


import com.trujillo.employee.management.application.exception.EmployeeNotFoundException;
import com.trujillo.employee.management.domain.model.Employee;
import com.trujillo.employee.management.domain.port.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        log.info("Getting all employees");
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        log.info("Getting employee with id: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        log.info("Creating new employee: {}", employee);
        return employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> createEmployees(List<Employee> employees) {
        log.info("Creating {} employees", employees.size());
        return employeeRepository.saveAll(employees);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        log.info("Updating employee with id: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setMiddleName(employeeDetails.getMiddleName());
        employee.setPaternalLastName(employeeDetails.getPaternalLastName());
        employee.setMaternalLastName(employeeDetails.getMaternalLastName());
        employee.setAge(employeeDetails.getAge());
        employee.setGender(employeeDetails.getGender());
        employee.setBirthDate(employeeDetails.getBirthDate());
        employee.setPosition(employeeDetails.getPosition());

        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}

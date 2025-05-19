package com.trujillo.employee.management.application.service;


import com.trujillo.employee.management.application.exception.EmployeeNotFoundException;
import com.trujillo.employee.management.domain.model.Employee;
import com.trujillo.employee.management.domain.model.Gender;
import com.trujillo.employee.management.domain.port.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .firstName("Hector")
                .middleName("Leonardo")
                .paternalLastName("Lopez")
                .maternalLastName("García")
                .age(30)
                .gender(Gender.MASCULINO)
                .birthDate(LocalDate.of(1990, 5, 15))
                .position("Software Developer")
                .build();

        Employee employee2 = Employee.builder()
                .id(2L)
                .firstName("Tania")
                .middleName("Laura")
                .paternalLastName("Mendez")
                .maternalLastName("Sánchez")
                .age(28)
                .gender(Gender.FEMENINO)
                .birthDate(LocalDate.of(1992, 8, 20))
                .position("Project Manager")
                .build();

        employeeList = Arrays.asList(employee, employee2);
    }

    @Test
    void getAllEmployees_ShouldReturnAllEmployees() {

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmployeeById_WithValidId_ShouldReturnEmployee() {

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Hector", result.getFirstName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_WithInvalidId_ShouldThrowException() {

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeById(999L);
        });
        verify(employeeRepository, times(1)).findById(999L);
    }

    @Test
    void createEmployee_ShouldReturnSavedEmployee() {

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getFirstName(), result.getFirstName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void createEmployees_ShouldReturnSavedEmployees() {

        when(employeeRepository.saveAll(anyList())).thenReturn(employeeList);

        List<Employee> result = employeeService.createEmployees(employeeList);

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).saveAll(employeeList);
    }

    @Test
    void updateEmployee_WithValidId_ShouldReturnUpdatedEmployee() {

        Employee updatedEmployee = Employee.builder()
                .id(1L)
                .firstName("Hector")
                .middleName("Leonardo")
                .paternalLastName("Lopez")
                .maternalLastName("García")
                .age(31) // Updated age
                .gender(Gender.MASCULINO)
                .birthDate(LocalDate.of(1990, 5, 15))
                .position("Senior Developer") // Updated position
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);


        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        assertNotNull(result);
        assertEquals(31, result.getAge());
        assertEquals("Senior Developer", result.getPosition());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateEmployee_WithInvalidId_ShouldThrowException() {

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.updateEmployee(999L, employee);
        });
        verify(employeeRepository, times(1)).findById(999L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_WithValidId_ShouldDeleteEmployee() {

        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);


        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteEmployee_WithInvalidId_ShouldThrowException() {

        when(employeeRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.deleteEmployee(999L);
        });
        verify(employeeRepository, times(1)).existsById(999L);
        verify(employeeRepository, never()).deleteById(anyLong());
    }
}
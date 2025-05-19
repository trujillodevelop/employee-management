package com.trujillo.employee.management.infrastructure.adapter.rest.controller;

import com.trujillo.employee.management.application.service.EmployeeService;
import com.trujillo.employee.management.domain.model.Employee;
import com.trujillo.employee.management.infrastructure.adapter.rest.dto.EmployeeDTO;
import com.trujillo.employee.management.infrastructure.adapter.rest.mapper.EmployeeDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee Management", description = "APIs for managing employees")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDTOMapper EmployeeDTOMapper;

    @GetMapping
    @Operation(
            summary = "Get all employees",
            description = "Retrieves a list of all employees registered in the system",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = EmployeeDTO.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> EmployeeDTOs = employees.stream()
                .map(EmployeeDTOMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(EmployeeDTOs);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get employee by ID",
            description = "Retrieves an employee by their ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = EmployeeDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(EmployeeDTOMapper.toDto(employee));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new employee",
            description = "Creates a new employee record",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Employee created successfully",
                            content = @Content(schema = @Schema(implementation = EmployeeDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO EmployeeDTO) {
        Employee employee = EmployeeDTOMapper.toDomain(EmployeeDTO);
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(EmployeeDTOMapper.toDto(createdEmployee), HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create multiple employees",
            description = "Creates multiple employee records in a single request",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Employees created successfully",
                            content = @Content(schema = @Schema(implementation = EmployeeDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    public ResponseEntity<List<EmployeeDTO>> createEmployees(@Valid @RequestBody List<EmployeeDTO> EmployeeDTOs) {
        List<Employee> employees = EmployeeDTOs.stream()
                .map(EmployeeDTOMapper::toDomain)
                .collect(Collectors.toList());

        List<Employee> createdEmployees = employeeService.createEmployees(employees);

        List<EmployeeDTO> createdEmployeeDTOs = createdEmployees.stream()
                .map(EmployeeDTOMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(createdEmployeeDTOs, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an employee",
            description = "Updates any data of an existing employee",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Employee updated successfully",
                            content = @Content(schema = @Schema(implementation = EmployeeDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDTO EmployeeDTO) {

        Employee employee = EmployeeDTOMapper.toDomain(EmployeeDTO);
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        return ResponseEntity.ok(EmployeeDTOMapper.toDto(updatedEmployee));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete an employee",
            description = "Deletes an employee by their ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Employee not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

package com.trujillo.employee.management.infrastructure.adapter.rest.mapper;

import com.trujillo.employee.management.domain.model.Employee;
import com.trujillo.employee.management.infrastructure.adapter.rest.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDTOMapper {
    public Employee toDomain(EmployeeDTO dto) {
        return Employee.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .paternalLastName(dto.getPaternalLastName())
                .maternalLastName(dto.getMaternalLastName())
                .age(dto.getAge())
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .position(dto.getPosition())
                .build();
    }

    public EmployeeDTO toDto(Employee domain) {
        return EmployeeDTO.builder()
                .id(domain.getId())
                .firstName(domain.getFirstName())
                .middleName(domain.getMiddleName())
                .paternalLastName(domain.getPaternalLastName())
                .maternalLastName(domain.getMaternalLastName())
                .age(domain.getAge())
                .gender(domain.getGender())
                .birthDate(domain.getBirthDate())
                .position(domain.getPosition())
                .build();
    }
}

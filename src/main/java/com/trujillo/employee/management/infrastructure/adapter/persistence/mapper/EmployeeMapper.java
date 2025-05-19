package com.trujillo.employee.management.infrastructure.adapter.persistence.mapper;

import com.trujillo.employee.management.domain.model.Employee;
import com.trujillo.employee.management.infrastructure.adapter.persistence.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toDomain(EmployeeEntity entity) {
        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .paternalLastName(entity.getPaternalLastName())
                .maternalLastName(entity.getMaternalLastName())
                .age(entity.getAge())
                .gender(entity.getGender())
                .birthDate(entity.getBirthDate())
                .position(entity.getPosition())
                .build();
    }

    public EmployeeEntity toEntity(Employee domain) {
        return EmployeeEntity.builder()
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

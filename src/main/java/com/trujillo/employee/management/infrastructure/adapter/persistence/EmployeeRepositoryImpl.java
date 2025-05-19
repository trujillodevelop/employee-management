package com.trujillo.employee.management.infrastructure.adapter.persistence;

import com.trujillo.employee.management.domain.model.Employee;
import com.trujillo.employee.management.domain.port.EmployeeRepository;
import com.trujillo.employee.management.infrastructure.adapter.persistence.entity.EmployeeEntity;
import com.trujillo.employee.management.infrastructure.adapter.persistence.mapper.EmployeeMapper;
import com.trujillo.employee.management.infrastructure.adapter.persistence.repository.JpaEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JpaEmployeeRepository jpaEmployeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAll() {
        return jpaEmployeeRepository.findAll().stream()
                .map(employeeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return jpaEmployeeRepository.findById(id)
                .map(employeeMapper::toDomain);
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = employeeMapper.toEntity(employee);
        EmployeeEntity savedEntity = jpaEmployeeRepository.save(employeeEntity);
        return employeeMapper.toDomain(savedEntity);
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        List<EmployeeEntity> entities = employees.stream()
                .map(employeeMapper::toEntity)
                .collect(Collectors.toList());

        return jpaEmployeeRepository.saveAll(entities).stream()
                .map(employeeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaEmployeeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaEmployeeRepository.existsById(id);
    }
}

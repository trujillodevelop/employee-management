package com.trujillo.employee.management.infrastructure.adapter.persistence.repository;

import com.trujillo.employee.management.infrastructure.adapter.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}

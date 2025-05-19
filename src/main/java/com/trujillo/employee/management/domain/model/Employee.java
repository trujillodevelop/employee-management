package com.trujillo.employee.management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String middleName;
    private String paternalLastName;
    private String maternalLastName;
    private Integer age;
    private Gender gender;
    private LocalDate birthDate;
    private String position;
}

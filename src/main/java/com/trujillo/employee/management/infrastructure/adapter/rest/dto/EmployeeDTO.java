package com.trujillo.employee.management.infrastructure.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trujillo.employee.management.domain.model.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Employee information")
public class EmployeeDTO {
    @Schema(description = "Employee ID", example = "1")
    private Long id;

    @NotBlank(message = "First name is required")
    @Schema(description = "Employee's first name", example = "Gustavo")
    private String firstName;

    @Schema(description = "Employee's middle name", example = "Adolfo")
    private String middleName;

    @NotBlank(message = "Paternal last name is required")
    @Schema(description = "Employee's paternal last name", example = "Trujillo")
    private String paternalLastName;

    @Schema(description = "Employee's maternal last name", example = "Parra")
    private String maternalLastName;

    @NotNull(message = "Age is required")
    @Positive(message = "Age must be positive")
    @Schema(description = "Employee's age", example = "30")
    private Integer age;

    @NotNull(message = "Gender is required")
    @Schema(description = "Employee's gender", example = "MASCULINO")
    private Gender gender;

    @NotNull(message = "Birthdate is required")
    @Past(message = "Birthdate must be in the past")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(description = "Employee's birthdate (dd-MM-yyyy)", example = "05-04-1988")
    private LocalDate birthDate;

    @NotBlank(message = "Position is required")
    @Schema(description = "Employee's position", example = "Desarrollador Java Sr")
    private String position;
}

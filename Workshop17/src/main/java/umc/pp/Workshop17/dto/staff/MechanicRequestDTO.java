package umc.pp.Workshop17.dto.staff;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record MechanicRequestDTO(

        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String taxId,
        @NotBlank String specialty,
        String certificationLevel,
        String phone,
        @Email String email,
        LocalDate hireDate
){}
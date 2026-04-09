package umc.pp.Workshop17.dto.staff.update;

import jakarta.validation.constraints.NotBlank;

public record MechanicUpdatePersonalInfoDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String phone,
        @NotBlank String email
){}
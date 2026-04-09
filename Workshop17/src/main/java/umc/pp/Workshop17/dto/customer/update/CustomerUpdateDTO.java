package umc.pp.Workshop17.dto.customer.update;

import jakarta.validation.constraints.NotBlank;

public record CustomerUpdateDTO(

        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String phone,
        @NotBlank String email

){}
package umc.pp.Workshop17.dto.customer;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(
        @NotBlank String street,
        @NotBlank String number,
        String complement,
        @NotBlank String neighborhood,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String zipCode
){}
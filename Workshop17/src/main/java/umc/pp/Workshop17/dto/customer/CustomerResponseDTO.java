package umc.pp.Workshop17.dto.customer;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID uuid,
        String firstName,
        String lastName,
        String taxId,
        String email,
        String phone,
        AddressResponseDTO address,
        boolean isActive,
        LocalDateTime registrationDate
) {
}

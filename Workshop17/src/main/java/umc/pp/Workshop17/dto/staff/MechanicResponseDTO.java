package umc.pp.Workshop17.dto.staff;

import java.util.UUID;

public record MechanicResponseDTO(
        UUID uuid,
        String firstName,
        String lastName,
        String specialty,
        String email,
        String taxId,
        String phone,
        boolean isActive
){}
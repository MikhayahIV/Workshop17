package umc.pp.Workshop17.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record VehicleRequestDTO(
        @NotBlank(message = "A placa é obrigatória")
        @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$", message = "Placa em formato inválido (ABC1D23)")
        String licensePlate,

        @NotBlank(message = "O VIN (chassi) é obrigatório")
        @Size(min = 17, max = 17, message = "O VIN deve ter exatamente 17 caracteres")
        String vin,

        @NotBlank(message = "A marca é obrigatória")
        String brand,

        @NotBlank(message = "O modelo é obrigatório")
        String model,

        @NotNull(message = "O ano de fabricação é obrigatório")
        Integer manufacturingYear,

        @NotBlank(message = "A cor é obrigatória")
        String color,

        String fuelType,

        String engineVersion,
        String transmissionType,
        Integer cylinderCount,

        @NotNull(message = "O ID do cliente proprietário é obrigatório")
        UUID customerId) {
}

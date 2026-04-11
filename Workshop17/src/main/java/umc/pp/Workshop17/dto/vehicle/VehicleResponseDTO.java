package umc.pp.Workshop17.dto.vehicle;

import java.util.UUID;

public record VehicleResponseDTO(

        UUID uuid,
        String licensePlate,
        String vin,
        String brand,
        String model,
        Integer manufacturingYear,
        String color,
        String fuelType,
        String engineVersion,
        String transmissionVersion,
        Integer cylinderCount,
        String customerId
){ }
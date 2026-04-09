package umc.pp.Workshop17.dto.services.ServiceOrder.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceOrderUpdateDTO(

        @NotBlank String mechanicDiagnostic,
        @NotNull @PositiveOrZero BigDecimal partsValue,
        @NotNull @PositiveOrZero BigDecimal laborValue,
        LocalDateTime estimatedDeliveryDate
){}
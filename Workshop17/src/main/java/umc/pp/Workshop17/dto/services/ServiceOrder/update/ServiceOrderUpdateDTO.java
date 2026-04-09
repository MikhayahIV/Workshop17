package umc.pp.Workshop17.dto.services.ServiceOrder.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceOrderUpdateDTO(

        @NotBlank String mechanicDiagnostic,
        @NotNull BigDecimal partsValue,
        @NotNull BigDecimal laborValue,
        LocalDateTime estimatedDeliveryDate
){}
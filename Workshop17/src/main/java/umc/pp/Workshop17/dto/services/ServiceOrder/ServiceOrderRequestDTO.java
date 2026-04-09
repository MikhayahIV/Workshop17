package umc.pp.Workshop17.dto.services.ServiceOrder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListRequestDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record ServiceOrderRequestDTO(
        @NotBlank String customerComplaint,
        @NotNull UUID customerId,
        @NotNull UUID vehicleId,
        UUID mechanicId,
        LocalDateTime estimatedDeliveryDate,
        @NotNull EntryCheckListRequestDTO entryChecklist
){}
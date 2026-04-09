package umc.pp.Workshop17.dto.services.entryCheckList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EntryCheckListRequestDTO(
        @NotNull Integer entryMileage,
        @NotBlank String fuelLevel,
        boolean hasScratches,
        boolean hasDents,
        boolean hasSpareTire,
        boolean hasPersonalItem,
        boolean functionalHeadLine,
        boolean hasLugWrench,
        String itemsLeftInVehicle,
        String tireCondition,
        @NotBlank String inspectorName,
        String generalNote
){}
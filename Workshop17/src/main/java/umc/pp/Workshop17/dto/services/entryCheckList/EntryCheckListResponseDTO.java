package umc.pp.Workshop17.dto.services.entryCheckList;

import java.time.LocalDateTime;

public record EntryCheckListResponseDTO(

        Long id,
        Integer entryMileage,
        String fuelLevel,
        boolean hasScratches,
        boolean hasDents,
        boolean hasSpareTire,
        boolean hasPersonalItem,
        boolean functionalHeadLine,
        boolean hasLugWrench,
        String itemsLeftInVehicle,
        String tireCondition,
        String inspectorName,
        LocalDateTime inspectionDate,
        String generalNote
){}
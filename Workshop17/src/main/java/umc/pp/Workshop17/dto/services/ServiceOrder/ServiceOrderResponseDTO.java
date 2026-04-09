package umc.pp.Workshop17.dto.services.ServiceOrder;

import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.dto.vehicle.VehicleResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceOrderResponseDTO(

        Long id,
        String protocolNumber,
        String customerComplaint,
        String mechanicDiagnostic,
        String status,
        BigDecimal partsValue,
        BigDecimal laborValue,
        BigDecimal totalValue,
        LocalDateTime entryDate,
        LocalDateTime estimatedDeliveryDate,
        LocalDateTime finishDate,
        CustomerResponseDTO customer,
        VehicleResponseDTO vehicle,
        MechanicResponseDTO mechanic,
        EntryCheckListResponseDTO entryChecklist
){}
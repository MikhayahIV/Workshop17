package umc.pp.Workshop17.mapper.services;

import org.springframework.stereotype.Component;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderRequestDTO;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListRequestDTO;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.mapper.customer.CustomerMapper;
import umc.pp.Workshop17.mapper.staff.mechanic.MechanicMapper;
import umc.pp.Workshop17.mapper.vehicle.VehicleMapper;
import umc.pp.Workshop17.model.customer.Customer;
import umc.pp.Workshop17.model.service.EntryCheckList;
import umc.pp.Workshop17.model.service.ServiceOrder;
import umc.pp.Workshop17.model.staff.Mechanic;
import umc.pp.Workshop17.model.vehicle.Vehicle;

import java.time.LocalDateTime;

@Component
public class ServiceOrderMapper {

    private final CustomerMapper customerMapper;
    private final VehicleMapper vehicleMapper;
    private final MechanicMapper mechanicMapper;

    public ServiceOrderMapper(CustomerMapper customerMapper, VehicleMapper vehicleMapper, MechanicMapper mechanicMapper) {
        this.customerMapper = customerMapper;
        this.vehicleMapper = vehicleMapper;
        this.mechanicMapper = mechanicMapper;
    }

    public ServiceOrder toEntity(ServiceOrderRequestDTO dto, String protocol, Customer customer, Vehicle vehicle, Mechanic mechanic, LocalDateTime estimatedDate) {

        return new ServiceOrder.Builder()
                .initiate(customer, vehicle, dto.customerComplaint(), protocol)
                .withChecklist(toCheckListEntity(dto.entryChecklist(), vehicle))
                .assignMechanic(mechanic)
                .setEstimates(estimatedDate)
                .build();
    }

    public EntryCheckList toCheckListEntity(EntryCheckListRequestDTO dto, Vehicle vehicle) {
        return new EntryCheckList.Builder()
                .vehicleInfo(vehicle, dto.entryMileage(), dto.fuelLevel(), dto.inspectorName())
                .damageAndItems(dto.hasScratches(), dto.hasDents(), dto.hasPersonalItem(), dto.itemsLeftInVehicle())
                .technicalCheck(dto.functionalHeadLine(), dto.hasSpareTire(), dto.hasLugWrench(), dto.tireCondition())
                .notes(dto.generalNote())
                .build();
    }

    public ServiceOrderResponseDTO toResponse(ServiceOrder so) {
        return new ServiceOrderResponseDTO(
                so.getId(),
                so.getProtocolNumber(),
                so.getCustomerComplaint(),
                so.getMechanicDiagnostic(),
                so.getStatus().name(),
                so.getPartsValue(),
                so.getLaborValue(),
                so.getTotalValue(),
                so.getEntryDate(),
                so.getEstimatedDeliveryDate(),
                so.getFinishDate(),
                customerMapper.toResponse(so.getCustomer()),
                vehicleMapper.toResponse(so.getVehicle()),
                so.getMechanic() != null ? mechanicMapper.toResponse(so.getMechanic()) : null,
                toCheckListResponse(so.getEntryChecklist())
        );
    }

    public EntryCheckListResponseDTO toCheckListResponse(EntryCheckList cl) {
        if (cl == null) return null;
        return new EntryCheckListResponseDTO(
                cl.getId(),
                cl.getEntryMileage(),
                cl.getFuelLevel(),
                cl.isHasScratches(),
                cl.isHasDents(),
                cl.isHasSpareTire(),
                cl.isHasPersonalItem(),
                cl.isFunctionalHeadLine(),
                cl.isHasLugWrench(),
                cl.getItemsLeftInVehicle(),
                cl.getTireCondition(),
                cl.getInspectorName(),
                cl.getInspectionDate(),
                cl.getGeneralNote()
        );
    }
}

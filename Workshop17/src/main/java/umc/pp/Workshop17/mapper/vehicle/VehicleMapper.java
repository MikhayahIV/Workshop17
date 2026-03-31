package umc.pp.Workshop17.mapper.vehicle;

import org.springframework.stereotype.Component;
import umc.pp.Workshop17.dto.vehicle.VehicleRequestDTO;
import umc.pp.Workshop17.dto.vehicle.VehicleResponseDTO;
import umc.pp.Workshop17.model.customer.Customer;
import umc.pp.Workshop17.model.vehicle.Vehicle;

@Component
public class VehicleMapper {

    public Vehicle toEntity(VehicleRequestDTO dto, Customer customer) {
        return new Vehicle.Builder()
                .basicInfo(dto.brand(), dto.model(), dto.licensePlate(), dto.manufacturingYear(), dto.color())
                .technicalDetails(dto.vin(), dto.fuel(), dto.engineVersion(), dto.transmissionType(), dto.cylinderCount())
                .forOwner(customer)
                .build();
    }

    public VehicleResponseDTO toResponse(Vehicle vehicle) {
        return new VehicleResponseDTO(
                vehicle.getUuid(),
                vehicle.getLicensePlate(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getManufactureYear(),
                vehicle.getColor(),
                vehicle.getEngineVersion(),
                vehicle.getOwner().getFirstName()
        );
    }
}

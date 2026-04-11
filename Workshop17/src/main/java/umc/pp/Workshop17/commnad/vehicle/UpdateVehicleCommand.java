package umc.pp.Workshop17.commnad.vehicle;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.vehicle.VehicleRequestDTO;
import umc.pp.Workshop17.dto.vehicle.VehicleResponseDTO;
import umc.pp.Workshop17.service.vehicle.VehicleService;

import java.util.UUID;

public class UpdateVehicleCommand implements Command<VehicleResponseDTO> {

    private final VehicleService service;
    private final UUID id;
    private final VehicleRequestDTO data;

    public UpdateVehicleCommand(VehicleService service, UUID id, VehicleRequestDTO data) {
        this.service = service;
        this.id = id;
        this.data = data;
    }

    @Override
    public VehicleResponseDTO execute() {
        return service.update(id,data);
    }
}

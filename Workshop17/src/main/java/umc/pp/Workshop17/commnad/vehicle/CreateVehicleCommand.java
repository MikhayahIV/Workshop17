package umc.pp.Workshop17.commnad.vehicle;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.vehicle.VehicleRequestDTO;
import umc.pp.Workshop17.dto.vehicle.VehicleResponseDTO;
import umc.pp.Workshop17.service.vehicle.VehicleService;

public class CreateVehicleCommand implements Command<VehicleResponseDTO> {

    private final VehicleService service;
    private final VehicleRequestDTO data;

    public CreateVehicleCommand(VehicleService service, VehicleRequestDTO data) {
        this.service = service;
        this.data = data;
    }

    @Override
    public VehicleResponseDTO execute() {
        return service.create(data);
    }
}

package umc.pp.Workshop17.commnad.vehicle;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.vehicle.VehicleResponseDTO;
import umc.pp.Workshop17.service.vehicle.VehicleService;

import java.util.List;

public class FindAllVehicleCommand implements Command<List<VehicleResponseDTO>> {

    private final VehicleService service;

    public FindAllVehicleCommand(VehicleService service) {
        this.service = service;
    }

    @Override
    public List<VehicleResponseDTO> execute() {
        return service.findAll();
    }
}

package umc.pp.Workshop17.commnad.vehicle;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.vehicle.VehicleResponseDTO;
import umc.pp.Workshop17.service.vehicle.VehicleService;

public class FindByPlateVehicleCommand implements Command<VehicleResponseDTO> {

    private final VehicleService service;
    private final String plate;

    public FindByPlateVehicleCommand(VehicleService service, String plate) {
        this.service = service;
        this.plate = plate;
    }

    @Override
    public VehicleResponseDTO execute() {
        return service.findByPlate(plate);
    }
}

package umc.pp.Workshop17.commnad.vehicle;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.service.vehicle.VehicleService;

import java.util.UUID;

public class DeleteVehicleCommand implements Command<Void> {

    private final VehicleService service;
    private final UUID id;

    public DeleteVehicleCommand(VehicleService service, UUID id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public Void execute() {
        service.delete(id);
        return null;
    }
}

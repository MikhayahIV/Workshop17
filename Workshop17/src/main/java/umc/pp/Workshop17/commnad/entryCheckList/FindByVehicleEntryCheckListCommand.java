package umc.pp.Workshop17.commnad.entryCheckList;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.service.services.EntryCheckListService;

import java.util.List;
import java.util.UUID;

public class FindByVehicleEntryCheckListCommand implements Command<List<EntryCheckListResponseDTO>> {

    private final EntryCheckListService service;
    private final UUID vehicleId;


    public FindByVehicleEntryCheckListCommand(EntryCheckListService service, UUID vehicleId) {
        this.service = service;
        this.vehicleId = vehicleId;
    }

    @Override
    public List<EntryCheckListResponseDTO> execute() {
        return service.listByVehicle(vehicleId);
    }
}

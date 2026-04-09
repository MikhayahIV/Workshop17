package umc.pp.Workshop17.commnad.entryCheckList;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListRequestDTO;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.service.services.EntryCheckListService;

import java.util.UUID;

public class CreateEntryCheckListCommand implements Command<EntryCheckListResponseDTO> {

    private final EntryCheckListService service;
    private final EntryCheckListRequestDTO data;
    private final UUID vehicleId;

    public CreateEntryCheckListCommand(EntryCheckListService service, EntryCheckListRequestDTO data, UUID vehicleId) {
        this.service = service;
        this.data = data;
        this.vehicleId = vehicleId;
    }

    @Override
    public EntryCheckListResponseDTO execute() {
        return service.createIsolated(vehicleId,data);
    }
}

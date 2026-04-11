package umc.pp.Workshop17.commnad.entryCheckList;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListRequestDTO;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.service.services.EntryCheckListService;

public class UpdateEntryCheckListCommand implements Command<EntryCheckListResponseDTO> {

    private final EntryCheckListService service;
    private final EntryCheckListRequestDTO data;
    private final Long id;

    public UpdateEntryCheckListCommand(EntryCheckListService service, EntryCheckListRequestDTO data, Long id) {
        this.service = service;
        this.data = data;
        this.id = id;
    }

    @Override
    public EntryCheckListResponseDTO execute() {
        return service.update(id,data);
    }
}


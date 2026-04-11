package umc.pp.Workshop17.commnad.entryCheckList;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.service.services.EntryCheckListService;

public class FindByIdEntryCheckListCommand implements Command<EntryCheckListResponseDTO> {

    private final EntryCheckListService service;
    private final Long id;

    public FindByIdEntryCheckListCommand(EntryCheckListService service, Long id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public EntryCheckListResponseDTO execute() {
        return service.findById(id);
    }
}

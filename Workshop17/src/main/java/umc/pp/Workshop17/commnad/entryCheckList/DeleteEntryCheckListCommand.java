package umc.pp.Workshop17.commnad.entryCheckList;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.service.services.EntryCheckListService;

public class DeleteEntryCheckListCommand implements Command<Void> {

    private final EntryCheckListService service;
    private final Long id;

    public DeleteEntryCheckListCommand(EntryCheckListService service, Long id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public Void execute() {
        service.delete(id);
        return null;
    }
}

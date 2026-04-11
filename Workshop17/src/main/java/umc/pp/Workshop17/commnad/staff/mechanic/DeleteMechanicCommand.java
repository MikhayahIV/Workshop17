package umc.pp.Workshop17.commnad.staff.mechanic;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.service.staff.mechanic.MechanicService;

import java.util.UUID;

public class DeleteMechanicCommand implements Command<Void> {
    private final MechanicService service;
    private final UUID id;

    public DeleteMechanicCommand(MechanicService service, UUID id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public Void execute() {
        service.delete(id);
        return null;
    }
}

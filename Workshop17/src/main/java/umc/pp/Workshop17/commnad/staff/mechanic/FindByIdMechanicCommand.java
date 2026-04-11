package umc.pp.Workshop17.commnad.staff.mechanic;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.service.staff.mechanic.MechanicService;

import java.util.UUID;

public class FindByIdMechanicCommand implements Command<MechanicResponseDTO> {

    private final MechanicService service;
    private final UUID id;

    public FindByIdMechanicCommand(MechanicService service, UUID id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public MechanicResponseDTO execute() {
        return service.findById(id);
    }
}

package umc.pp.Workshop17.commnad.staff.mechanic;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.dto.staff.update.MechanicUpdateExpertiseDTO;
import umc.pp.Workshop17.service.staff.mechanic.MechanicService;

import java.util.UUID;

public class UpdateExpertiseMechanicCommand implements Command<MechanicResponseDTO> {

    private final MechanicService service;

    private final UUID id;
    private final MechanicUpdateExpertiseDTO data;

    public UpdateExpertiseMechanicCommand(MechanicService service, UUID id, MechanicUpdateExpertiseDTO data) {
        this.service = service;
        this.id = id;
        this.data = data;
    }

    @Override
    public MechanicResponseDTO execute() {
        return service.updateExpertise(id,data);
    }
}

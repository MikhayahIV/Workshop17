package umc.pp.Workshop17.commnad.staff.mechanic;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.dto.staff.update.MechanicUpdatePersonalInfoDTO;
import umc.pp.Workshop17.service.staff.mechanic.MechanicService;

import java.util.UUID;

public class UpdatePersonalInfoMechanicCommand implements Command<MechanicResponseDTO> {

    private final MechanicService service;
    private final UUID id;
    private final MechanicUpdatePersonalInfoDTO data;

    public UpdatePersonalInfoMechanicCommand(MechanicService service, UUID id, MechanicUpdatePersonalInfoDTO data) {
        this.service = service;
        this.id = id;
        this.data = data;
    }

    @Override
    public MechanicResponseDTO execute() {
        return service.updatePersonalInfo(id,data);
    }
}

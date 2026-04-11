package umc.pp.Workshop17.commnad.staff.mechanic;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.staff.MechanicRequestDTO;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.service.staff.mechanic.MechanicService;

public class CreateMechanicCommand implements Command<MechanicResponseDTO> {

    private final MechanicService service;
    private final MechanicRequestDTO data;

    public CreateMechanicCommand(MechanicService service, MechanicRequestDTO data) {
        this.service = service;
        this.data = data;
    }

    @Override
    public MechanicResponseDTO execute() {
        return service.create(data);
    }
}

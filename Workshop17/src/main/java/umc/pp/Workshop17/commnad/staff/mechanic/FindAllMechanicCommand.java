package umc.pp.Workshop17.commnad.staff.mechanic;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.service.staff.mechanic.MechanicService;

import java.util.List;

public class FindAllMechanicCommand implements Command<List<MechanicResponseDTO>> {

    private final MechanicService service;

    public FindAllMechanicCommand(MechanicService service) {
        this.service = service;
    }

    @Override
    public List<MechanicResponseDTO> execute() {
        return service.listAll();
    }
}

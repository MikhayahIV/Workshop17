package umc.pp.Workshop17.commnad.staff.mechanic;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.service.staff.mechanic.MechanicService;

public class FindByTaxIdMechanicCommand implements Command<MechanicResponseDTO> {

    private final MechanicService service;
    private final String taxId;

    public FindByTaxIdMechanicCommand(MechanicService service, String taxId) {
        this.service = service;
        this.taxId = taxId;
    }

    @Override
    public MechanicResponseDTO execute() {
        return service.findByTaxId(taxId);
    }
}

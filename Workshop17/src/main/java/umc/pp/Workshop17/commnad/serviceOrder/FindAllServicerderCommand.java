package umc.pp.Workshop17.commnad.serviceOrder;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.service.services.ServiceOrderService;

import java.util.List;

public class FindAllServicerderCommand implements Command<List<ServiceOrderResponseDTO>> {

    private final ServiceOrderService service;

    public FindAllServicerderCommand(ServiceOrderService service) {
        this.service = service;
    }

    @Override
    public List<ServiceOrderResponseDTO> execute() {
        return service.listAll();
    }
}

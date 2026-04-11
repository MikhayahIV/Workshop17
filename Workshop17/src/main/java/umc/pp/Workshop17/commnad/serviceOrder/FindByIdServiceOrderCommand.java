package umc.pp.Workshop17.commnad.serviceOrder;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.service.services.ServiceOrderService;

public class FindByIdServiceOrderCommand implements Command<ServiceOrderResponseDTO> {

    private final ServiceOrderService service;

    private final Long id;

    public FindByIdServiceOrderCommand(ServiceOrderService service, Long id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public ServiceOrderResponseDTO execute() {
        return service.findById(id);
    }
}

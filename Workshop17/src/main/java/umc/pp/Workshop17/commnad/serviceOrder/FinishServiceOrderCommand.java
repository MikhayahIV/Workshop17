package umc.pp.Workshop17.commnad.serviceOrder;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.service.services.ServiceOrderService;

public class FinishServiceOrderCommand implements Command<ServiceOrderResponseDTO> {

    private ServiceOrderService service;
    private Long id;

    public FinishServiceOrderCommand(ServiceOrderService service, Long id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public ServiceOrderResponseDTO execute() {
        return service.finish(id);
    }
}

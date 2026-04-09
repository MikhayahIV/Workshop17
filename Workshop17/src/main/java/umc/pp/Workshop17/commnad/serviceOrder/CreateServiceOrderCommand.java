package umc.pp.Workshop17.commnad.serviceOrder;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderRequestDTO;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.service.services.ServiceOrderService;

public class CreateServiceOrderCommand implements Command<ServiceOrderResponseDTO> {

    private final ServiceOrderService service;
    private final ServiceOrderRequestDTO data;

    public CreateServiceOrderCommand(ServiceOrderService service, ServiceOrderRequestDTO data) {
        this.service = service;
        this.data = data;
    }

    @Override
    public ServiceOrderResponseDTO execute() {
        return service.create(data);
    }
}

package umc.pp.Workshop17.commnad.serviceOrder;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.dto.services.ServiceOrder.update.ServiceOrderUpdateDTO;
import umc.pp.Workshop17.service.services.ServiceOrderService;


public class UpdateServiceOrderCommand implements Command<ServiceOrderResponseDTO> {

    private final ServiceOrderService service;
    private final Long id;
    private final ServiceOrderUpdateDTO data;

    public UpdateServiceOrderCommand(ServiceOrderService service, Long id, ServiceOrderUpdateDTO data) {
        this.service = service;
        this.id = id;
        this.data = data;
    }

    @Override
    public ServiceOrderResponseDTO execute() {
        return service.updateExecution(id,data);
    }
}

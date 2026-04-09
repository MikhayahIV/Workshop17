package umc.pp.Workshop17.commnad.serviceOrder;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.service.services.ServiceOrderService;

public class CancelServiceOrderCommand implements Command<ServiceOrderResponseDTO> {

    private ServiceOrderService service;
    private Long id;
    private String reason;

    public CancelServiceOrderCommand(ServiceOrderService service, Long id, String reason) {
        this.service = service;
        this.id = id;
        this.reason = reason;
    }




    @Override
    public ServiceOrderResponseDTO execute() {
        return service.cancel(id, reason);
    }
}

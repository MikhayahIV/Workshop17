package umc.pp.Workshop17.commnad.serviceOrder;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.service.services.ServiceOrderService;

public class DeleteServiceOrderCommand implements Command<Void> {


    private final ServiceOrderService service;

    private final Long id;

    public DeleteServiceOrderCommand(ServiceOrderService service, Long id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public Void execute() {
        service.delete(id);
        return null;
    }
}

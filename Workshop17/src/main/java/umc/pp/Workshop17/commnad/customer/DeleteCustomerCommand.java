package umc.pp.Workshop17.commnad.customer;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.service.customer.CustomerService;

import java.util.UUID;

public class DeleteCustomerCommand implements Command<Void> {
    private final CustomerService service;
    private final UUID id;

    public DeleteCustomerCommand(CustomerService service, UUID id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public Void execute() {
        service.delete(id);
        return null;
    }

}

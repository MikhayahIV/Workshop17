package umc.pp.Workshop17.commnad.customer;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.service.customer.CustomerService;

import java.util.UUID;

public class ToggleStatusCustomerCommand implements Command<CustomerResponseDTO> {

    private final CustomerService service;
    private final UUID id;

    public ToggleStatusCustomerCommand(CustomerService service, UUID id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public CustomerResponseDTO execute() {
        return service.toggleStatus(id);
    }
}

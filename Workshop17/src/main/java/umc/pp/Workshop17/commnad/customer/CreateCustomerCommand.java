package umc.pp.Workshop17.commnad.customer;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.customer.CustomerRequestDTO;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.service.customer.CustomerService;

public class CreateCustomerCommand implements Command<CustomerResponseDTO> {

    private final CustomerService service;
    private final CustomerRequestDTO data;

    public CreateCustomerCommand(CustomerService service, CustomerRequestDTO data) {
        this.service = service;
        this.data = data;
    }

    @Override
    public CustomerResponseDTO execute() {
        return service.create(data);
    }
}

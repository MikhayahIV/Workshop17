package umc.pp.Workshop17.commnad.customer;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.service.customer.CustomerService;

import java.util.List;

public class FindAllCustomerCommand implements Command<List<CustomerResponseDTO>> {

    private final CustomerService service;

    public FindAllCustomerCommand(CustomerService service) {
        this.service = service;
    }

    @Override
    public List<CustomerResponseDTO> execute() {
        return service.findAll();
    }
}

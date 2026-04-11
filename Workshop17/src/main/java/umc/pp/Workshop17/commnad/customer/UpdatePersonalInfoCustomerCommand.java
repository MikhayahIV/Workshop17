package umc.pp.Workshop17.commnad.customer;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.dto.customer.update.CustomerUpdateDTO;
import umc.pp.Workshop17.service.customer.CustomerService;

import java.util.UUID;

public class UpdatePersonalInfoCustomerCommand implements Command<CustomerResponseDTO> {

    private final CustomerService service;
    private final UUID id;
    private final CustomerUpdateDTO data;

    public UpdatePersonalInfoCustomerCommand(CustomerService service, UUID id, CustomerUpdateDTO data) {
        this.service = service;
        this.id = id;
        this.data = data;
    }

    @Override
    public CustomerResponseDTO execute() {
        return service.updatePersonalInfo(id,data);
    }
}

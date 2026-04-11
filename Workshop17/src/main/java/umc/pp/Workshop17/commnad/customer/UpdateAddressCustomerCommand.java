package umc.pp.Workshop17.commnad.customer;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.customer.AddressRequestDTO;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.service.customer.CustomerService;

import java.util.UUID;

public class UpdateAddressCustomerCommand implements Command<CustomerResponseDTO> {
    private final CustomerService service;
    private final UUID id;
    private final AddressRequestDTO data;

    public UpdateAddressCustomerCommand(CustomerService service, UUID id, AddressRequestDTO data) {
        this.service = service;
        this.id = id;
        this.data = data;
    }

    @Override
    public CustomerResponseDTO execute() {
        return service.updateAddress(id,data);
    }
}

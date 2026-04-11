package umc.pp.Workshop17.commnad.customer;

import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.service.customer.CustomerService;


public class FindByTaxIdCustomerCommand implements Command<CustomerResponseDTO> {
    private final CustomerService service;
    private final String taxId;

    public FindByTaxIdCustomerCommand(CustomerService service, String taxId) {
        this.service = service;
        this.taxId = taxId;
    }

    @Override
    public CustomerResponseDTO execute() {
        return service.findByTaxId(taxId);
    }
}

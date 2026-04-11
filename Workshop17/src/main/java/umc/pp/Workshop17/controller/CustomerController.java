package umc.pp.Workshop17.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.commnad.customer.*;
import umc.pp.Workshop17.dto.customer.AddressRequestDTO;
import umc.pp.Workshop17.dto.customer.CustomerRequestDTO;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.dto.customer.update.CustomerUpdateDTO;
import umc.pp.Workshop17.service.customer.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody @Valid CustomerRequestDTO dto) {
        Command<CustomerResponseDTO> command = new CreateCustomerCommand(service,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(command.execute());
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponseDTO>> findAll(){
        Command<List<CustomerResponseDTO>> command = new FindAllCustomerCommand(service);
        return ResponseEntity.ok(command.execute());
    }

    @PatchMapping("/{id}/update-personal-info")
    public ResponseEntity<CustomerResponseDTO> updatePersonalInfo(@PathVariable UUID id,@RequestBody @Valid CustomerUpdateDTO dto){
        Command<CustomerResponseDTO> command = new UpdatePersonalInfoCustomerCommand(service,id,dto);
        return ResponseEntity.ok(command.execute());
    }

    @PatchMapping("/{id}/update-address")
    public ResponseEntity<CustomerResponseDTO> updateAddress(@PathVariable UUID id, @RequestBody @Valid AddressRequestDTO dto){
        Command<CustomerResponseDTO> command = new UpdateAddressCustomerCommand(service,id,dto);
        return ResponseEntity.ok(command.execute());
    }

    @GetMapping("/tax-id/{taxId}")
    public ResponseEntity<CustomerResponseDTO> findByTaxId(@PathVariable String taxId){
        Command<CustomerResponseDTO> command = new FindByTaxIdCustomerCommand(service, taxId);
        return ResponseEntity.ok(command.execute());
    }

    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<CustomerResponseDTO> toggleStatus(@PathVariable UUID id){
        Command<CustomerResponseDTO> command = new ToggleStatusCustomerCommand(service,id);
        return ResponseEntity.ok(command.execute());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        Command<Void> command = new DeleteCustomerCommand(service,id);
        command.execute();
        return ResponseEntity.noContent().build();
    }
}

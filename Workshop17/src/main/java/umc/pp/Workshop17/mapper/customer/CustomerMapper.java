package umc.pp.Workshop17.mapper.customer;

import org.springframework.stereotype.Component;
import umc.pp.Workshop17.dto.customer.AddressRequestDTO;
import umc.pp.Workshop17.dto.customer.AddressResponseDTO;
import umc.pp.Workshop17.dto.customer.CustomerRequestDTO;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.model.customer.Address;
import umc.pp.Workshop17.model.customer.Customer;

@Component
public class CustomerMapper {
    public Customer toEntity(CustomerRequestDTO dto){

        return new Customer.Builder()
                .personalInfo(dto.firstName(), dto.lastName(), dto.taxId(), dto.email(), dto.phone())
                .withAddress(addAddress(dto.address()))
                .build();
    }

    public Address addAddress(AddressRequestDTO dto){
        if(dto == null) return null;

        return new Address.Builder()
                .streetInfo(dto.street(), dto.number(), dto.complement())
                .location(dto.neighborhood(), dto.city(), dto.state(), dto.zipCode())
                .build();
    }


    public CustomerResponseDTO toResponse(Customer customer){
        if(customer == null) return null;
        return  new CustomerResponseDTO(
                customer.getUuid(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getTaxId(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                addressToResponse(customer.getAddress()),
                customer.isActive(),
                customer.getRegistrationDate());
    }

    public AddressResponseDTO addressToResponse(Address address){
        if(address == null) return null;

        return new AddressResponseDTO(
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode());
    }
}

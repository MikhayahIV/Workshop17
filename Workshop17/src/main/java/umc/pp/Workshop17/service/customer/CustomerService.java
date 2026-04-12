package umc.pp.Workshop17.service.customer;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.pp.Workshop17.dto.customer.AddressRequestDTO;
import umc.pp.Workshop17.dto.customer.CustomerRequestDTO;
import umc.pp.Workshop17.dto.customer.CustomerResponseDTO;
import umc.pp.Workshop17.dto.customer.update.CustomerUpdateDTO;
import umc.pp.Workshop17.exception.BusinessException;
import umc.pp.Workshop17.exception.ResourceNotFoundException;
import umc.pp.Workshop17.mapper.customer.CustomerMapper;
import umc.pp.Workshop17.model.customer.Address;
import umc.pp.Workshop17.model.customer.Customer;
import umc.pp.Workshop17.repository.customer.CustomerRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerResponseDTO create(CustomerRequestDTO dto){
        if(customerRepository.existsBytaxId(dto.taxId())){
            throw  new BusinessException("Já existe um cliente cadastrado com este documento.");
        }
        Customer customer = customerMapper.toEntity(dto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO findByTaxId(String taxId){
        return customerRepository.findBytaxId(taxId)
                .map(customerMapper::toResponse)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario nao encontrado"));
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> findAll(){
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomerResponseDTO toggleStatus(UUID uuid){
        Customer customer = customerRepository.findById(uuid)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario nao encontrado"));

        customer.toggleStatus();

        return customerMapper.toResponse(customer);
    }

    @Transactional
    public CustomerResponseDTO updatePersonalInfo(UUID uuid,CustomerUpdateDTO dto){
        Customer customer = customerRepository.findById(uuid).orElseThrow(()-> new ResourceNotFoundException("Usuario nao encontrado"));
        customer.updatePersonalData(dto.firstName(),dto.lastName(), dto.phone(), dto.email());
        return customerMapper.toResponse(customer);
    }

    @Transactional
    public CustomerResponseDTO updateAddress(UUID uuid, AddressRequestDTO dto){
        Customer customer = customerRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
        Address addressUpdate = customerMapper.addAddress(dto);
        customer.changeAddress(addressUpdate);
        return customerMapper.toResponse(customer);
    }

    @Transactional
    public void delete(UUID uuid){
        if(!customerRepository.existsById(uuid)){
            throw new EntityNotFoundException("Usuario nao encontrado");
        }
        customerRepository.deleteById(uuid);
    }

}

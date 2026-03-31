package umc.pp.Workshop17.service.vehicle;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.pp.Workshop17.dto.vehicle.VehicleRequestDTO;
import umc.pp.Workshop17.dto.vehicle.VehicleResponseDTO;
import umc.pp.Workshop17.mapper.vehicle.VehicleMapper;
import umc.pp.Workshop17.model.customer.Customer;
import umc.pp.Workshop17.model.vehicle.Vehicle;
import umc.pp.Workshop17.repository.customer.CustomerRepository;
import umc.pp.Workshop17.repository.vehicle.VehicleRepository;

import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleService(VehicleRepository vehicleRepository, CustomerRepository customerRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.vehicleMapper = vehicleMapper;
    }


    @Transactional
    public VehicleResponseDTO create(VehicleRequestDTO dto){
        Customer owner = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        Vehicle vehicle = vehicleMapper.toEntity(dto, owner);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toResponse(savedVehicle);
    }
    // equals insert into vehicle

    //todo criar os metodos de listar todos e atualizar veiculo

    public VehicleResponseDTO findById(UUID uuid){
        return vehicleRepository.findById(uuid)
                .map(vehicleMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Veiculo nao encontrado "));
    }


    @Transactional
    public void delete(UUID uuid){
        if(vehicleRepository.existsById(uuid)) throw  new EntityNotFoundException("Nao foi possivel deletar, veiculo nao encotrado");
        vehicleRepository.deleteById(uuid);
    }

}

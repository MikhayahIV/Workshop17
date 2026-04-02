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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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


    public VehicleResponseDTO findById(UUID uuid){
        return vehicleRepository.findById(uuid)
                .map(vehicleMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Veiculo nao encontrado "));
    }

    public List<VehicleResponseDTO> findAll(){
        return vehicleRepository.findAll().stream()
                .map(vehicleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public VehicleResponseDTO update(UUID uuid, VehicleRequestDTO dto){
        Vehicle existingVehicle = vehicleRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado"));
        Customer originalOwner = existingVehicle.getOwner();
        Vehicle updatedVehicle = new Vehicle.Builder()
                .withId(uuid)
                .basicInfo(dto.brand(), dto.model(), dto.licensePlate(), dto.manufacturingYear(), dto.color())
                .technicalDetails(dto.vin(), dto.fuel(), dto.engineVersion(), dto.transmissionType(), dto.cylinderCount())
                .forOwner(originalOwner)
                .build();
        return vehicleMapper.toResponse(vehicleRepository.save(updatedVehicle));
    }

    @Transactional
    public void delete(UUID uuid){
        if(!vehicleRepository.existsById(uuid)) {
            throw  new EntityNotFoundException("Nao foi possivel deletar, veiculo nao encotrado");
        }
        vehicleRepository.deleteById(uuid);
    }

}

package umc.pp.Workshop17.service.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderRequestDTO;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.dto.services.ServiceOrder.update.ServiceOrderUpdateDTO;
import umc.pp.Workshop17.exception.ResourceNotFoundException;
import umc.pp.Workshop17.mapper.services.ServiceOrderMapper;
import umc.pp.Workshop17.model.customer.Customer;
import umc.pp.Workshop17.model.service.ServiceOrder;
import umc.pp.Workshop17.model.staff.Mechanic;
import umc.pp.Workshop17.model.vehicle.Vehicle;
import umc.pp.Workshop17.repository.customer.CustomerRepository;
import umc.pp.Workshop17.repository.service.ServiceOrderRepository;
import umc.pp.Workshop17.repository.staff.MechanicRepository;
import umc.pp.Workshop17.repository.vehicle.VehicleRepository;
import umc.pp.Workshop17.service.utility.GenerateProtocol;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceOrderService implements GenerateProtocol {

    private final ServiceOrderRepository soRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final MechanicRepository mechanicRepository;
    private final ServiceOrderMapper mapper;

    public ServiceOrderService(ServiceOrderRepository soRepository, CustomerRepository customerRepository, VehicleRepository vehicleRepository, MechanicRepository mechanicRepository, ServiceOrderMapper mapper) {
        this.soRepository = soRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.mechanicRepository = mechanicRepository;
        this.mapper = mapper;
    }

    @Override
    public String generateProtocol() {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String random = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "OS-" + year + "-" + random;
    }

    @Transactional
    public ServiceOrderResponseDTO create(ServiceOrderRequestDTO dto) {
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        Vehicle vehicle = vehicleRepository.findById(dto.vehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
        Mechanic mechanic = null;
        if (dto.mechanicId() != null) {
            mechanic = mechanicRepository.findById(dto.mechanicId())
                    .orElseThrow(() -> new ResourceNotFoundException("Mecânico não encontrado"));
        }
        LocalDateTime estimated = (dto.estimatedDeliveryDate() != null)
                ? dto.estimatedDeliveryDate() : LocalDateTime.now().plusDays(3);
        String protocol = generateProtocol();
        ServiceOrder so = mapper.toEntity(
                dto,
                protocol,
                customer,
                vehicle,
                mechanic,
                estimated
        );
        return mapper.toResponse(soRepository.save(so));
    }

    @Transactional(readOnly = true)
    public ServiceOrderResponseDTO findById(Long id) {
        return soRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<ServiceOrderResponseDTO> listAll() {
        return soRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ServiceOrderResponseDTO updateExecution(Long id, ServiceOrderUpdateDTO dto) {
        ServiceOrder so = soRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OS não encontrada"));
        so.updateExecutionDetails(dto.mechanicDiagnostic(), dto.partsValue(), dto.laborValue());
        if (dto.estimatedDeliveryDate() != null) {
            so.updateEstimatedDate(dto.estimatedDeliveryDate());
        }
        return mapper.toResponse(so);
    }

    @Transactional
    public ServiceOrderResponseDTO finish(Long id) {
        ServiceOrder so = soRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada"));
        so.finishOS();
        return mapper.toResponse(so);
    }

    @Transactional
    public ServiceOrderResponseDTO cancel(Long id){
        ServiceOrder so = soRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ordem de servico nao encontrada"));
        so.cancelOS();
        return mapper.toResponse(so);
    }

    @Transactional
    public void delete(Long id) {
        if (!soRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ordem de Serviço não encontrada");
        }
        soRepository.deleteById(id);
    }

}

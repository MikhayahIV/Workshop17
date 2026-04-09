package umc.pp.Workshop17.service.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListRequestDTO;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.exception.ResourceNotFoundException;
import umc.pp.Workshop17.mapper.services.ServiceOrderMapper;
import umc.pp.Workshop17.model.service.EntryCheckList;
import umc.pp.Workshop17.model.vehicle.Vehicle;
import umc.pp.Workshop17.repository.service.EntryCheckListRepository;
import umc.pp.Workshop17.repository.vehicle.VehicleRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EntryCheckListService {

    private final EntryCheckListRepository repository;
    private final VehicleRepository vehicleRepository;
    private final ServiceOrderMapper mapper;

    public EntryCheckListService(EntryCheckListRepository repository, VehicleRepository vehicleRepository, ServiceOrderMapper mapper) {
        this.repository = repository;
        this.vehicleRepository = vehicleRepository;
        this.mapper = mapper;
    }


    @Transactional
    public EntryCheckListResponseDTO createIsolated(UUID vehicleId, EntryCheckListRequestDTO dto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
        EntryCheckList checklist = new EntryCheckList.Builder()
                .vehicleInfo(vehicle, dto.entryMileage(), dto.fuelLevel(), dto.inspectorName())
                .damageAndItems(dto.hasScratches(), dto.hasDents(), dto.hasPersonalItem(), dto.itemsLeftInVehicle())
                .technicalCheck(dto.functionalHeadLine(), dto.hasSpareTire(), dto.hasLugWrench(), dto.tireCondition())
                .notes(dto.generalNote())
                .build();
        return mapper.toCheckListResponse(repository.save(checklist));
    }

    @Transactional
    public EntryCheckListResponseDTO update(Long id, EntryCheckListRequestDTO dto) {
        EntryCheckList checklist = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist não encontrado"));
        checklist.updateNotes(dto.generalNote(), dto.itemsLeftInVehicle());
        return mapper.toCheckListResponse(repository.save(checklist));
    }

    @Transactional(readOnly = true)
    public EntryCheckListResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toCheckListResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist não encontrado"));
    }


    @Transactional(readOnly = true)
    public List<EntryCheckListResponseDTO> listByVehicle(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));

        return repository.findByVehicle(vehicle).stream()
                .map(mapper::toCheckListResponse)
                .collect(Collectors.toList());
    }

   @Transactional(readOnly = true)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Checklist não encontrado");
        }
        repository.deleteById(id);
    }
}

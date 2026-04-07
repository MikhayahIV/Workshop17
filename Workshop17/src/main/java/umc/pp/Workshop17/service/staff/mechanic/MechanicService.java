package umc.pp.Workshop17.service.staff.mechanic;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.pp.Workshop17.dto.staff.MechanicRequestDTO;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.exception.BusinessException;
import umc.pp.Workshop17.mapper.staff.mechanic.MechanicMapper;
import umc.pp.Workshop17.model.staff.Mechanic;
import umc.pp.Workshop17.repository.service.ServiceOrderRepository;
import umc.pp.Workshop17.repository.staff.MechanicRepository;
import umc.pp.Workshop17.service.staff.utility.GenerateEmployee;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MechanicService implements GenerateEmployee {
    private final MechanicMapper mapper;
    private final MechanicRepository mechanicrepository;
    private final ServiceOrderRepository soRepository;


    public MechanicService(MechanicMapper mapper, MechanicRepository mechanicrepository, ServiceOrderRepository soRepository) {
        this.mapper = mapper;
        this.mechanicrepository = mechanicrepository;
        this.soRepository = soRepository;
    }


    @Override
    public String generateEmployeeID() {
        String year = String.valueOf(LocalDate.now().getYear());
        String id = UUID.randomUUID().toString().substring(0,4).toUpperCase();
        return "MEC-"+year+"-"+id;
    }

    @Transactional
    public MechanicResponseDTO create(MechanicRequestDTO dto){
        if(mechanicrepository.existsByTaxID(dto.taxId())){
            throw new BusinessException("Já existe um mecânico cadastrado com este documento.");
        }
            String employeeId = generateEmployeeID();
            Mechanic mechanic = mapper.toEntity(dto,employeeId);
            return  mapper.toResponse(mechanicrepository.save(mechanic));
    }

    public MechanicResponseDTO findById(UUID uuid){
        return mechanicrepository.findById(uuid)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Mecanico nao encontrado"));
    }

    public List<MechanicResponseDTO> listAll(){
        return mechanicrepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public MechanicResponseDTO toggleStatus(UUID uuid){
        Mechanic mechanic = mechanicrepository.findById(uuid)
                .orElseThrow(()-> new EntityNotFoundException("mecanico nao encontrado"));
        mechanic.toggleStatus();

       return mapper.toResponse(mechanic);
    }


    @Transactional
    public void delete(UUID uuid){
        Mechanic mechanic = mechanicrepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Mecânico não encontrado"));
        if (soRepository.existsByMechanic(mechanic)) {
            throw new BusinessException("Não é possível excluir: o mecânico possui histórico de serviços. Use a inativação.");
        }
        mechanicrepository.deleteById(uuid);
    }
}

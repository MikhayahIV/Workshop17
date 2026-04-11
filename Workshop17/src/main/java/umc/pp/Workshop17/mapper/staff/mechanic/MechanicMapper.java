package umc.pp.Workshop17.mapper.staff.mechanic;

import org.springframework.stereotype.Component;
import umc.pp.Workshop17.dto.staff.MechanicRequestDTO;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.model.staff.Mechanic;

@Component
public class MechanicMapper {

    public Mechanic toEntity(MechanicRequestDTO dto, String employeeID){
        return new Mechanic.Builder()
                .personalInfo(dto.firstName(),dto.lastName(), dto.taxId(), dto.email(), dto.phone())
                .professionalInfo(employeeID,dto.specialty(), dto.certificationLevel(),dto.hireDate())
                .build();
    }

    public MechanicResponseDTO toResponse(Mechanic mechanic){
        return new MechanicResponseDTO(
                mechanic.getUuid(),
                mechanic.getFirstName(),
                mechanic.getLastName(),
                mechanic.getSpecialty(),
                mechanic.getEmail(),
                mechanic.getTaxId(),
                mechanic.getPhone(),
                mechanic.isActive());
    }
}

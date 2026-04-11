package umc.pp.Workshop17.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.commnad.staff.mechanic.*;
import umc.pp.Workshop17.dto.staff.MechanicRequestDTO;
import umc.pp.Workshop17.dto.staff.MechanicResponseDTO;
import umc.pp.Workshop17.dto.staff.update.MechanicUpdateExpertiseDTO;
import umc.pp.Workshop17.dto.staff.update.MechanicUpdatePersonalInfoDTO;
import umc.pp.Workshop17.service.staff.mechanic.MechanicService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mechanics")
public class MechanicController {

    private final MechanicService service;

    public MechanicController(MechanicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MechanicResponseDTO> create(@RequestBody @Valid MechanicRequestDTO dto) {
        Command<MechanicResponseDTO> command = new CreateMechanicCommand(service,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(command.execute());
    }

    @GetMapping
    public ResponseEntity<List<MechanicResponseDTO>> findAll() {
        Command<List<MechanicResponseDTO>> command = new FindAllMechanicCommand(service);
        return ResponseEntity.ok(command.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MechanicResponseDTO> findById(@PathVariable UUID id) {
        Command<MechanicResponseDTO> command = new FindByIdMechanicCommand(service, id);
        return ResponseEntity.ok(command.execute());
    }

    @GetMapping("/tax-id/{taxId}")
    public ResponseEntity<MechanicResponseDTO> findByTaxId(@PathVariable String taxId) {
        Command<MechanicResponseDTO> command = new FindByTaxIdMechanicCommand(service, taxId);
        return ResponseEntity.ok(command.execute());
    }

    @PatchMapping("/{id}/update-personal-info")
    public ResponseEntity<MechanicResponseDTO> updatePersonalInfo(@PathVariable UUID id, @RequestBody @Valid MechanicUpdatePersonalInfoDTO dto) {
        Command<MechanicResponseDTO> command = new UpdatePersonalInfoMechanicCommand(service, id, dto);
        return ResponseEntity.ok(command.execute());
    }

    @PatchMapping("/{id}/update-expertise")
    public ResponseEntity<MechanicResponseDTO> updateExpertise(@PathVariable UUID id, @RequestBody @Valid MechanicUpdateExpertiseDTO dto) {
        Command<MechanicResponseDTO> command = new UpdateExpertiseMechanicCommand(service, id, dto);
        return ResponseEntity.ok(command.execute());
    }

    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<MechanicResponseDTO> toggleStatus(@PathVariable UUID id) {
        Command<MechanicResponseDTO> command = new ToggleStatusMechanicCommand(service, id);
        return ResponseEntity.ok(command.execute());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Command<Void> command = new DeleteMechanicCommand(service, id);
        command.execute();
        return ResponseEntity.noContent().build();
    }
}

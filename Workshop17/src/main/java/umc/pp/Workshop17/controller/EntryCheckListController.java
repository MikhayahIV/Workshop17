package umc.pp.Workshop17.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.commnad.entryCheckList.*;
import umc.pp.Workshop17.commnad.serviceOrder.DeleteServiceOrderCommand;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListRequestDTO;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.service.services.EntryCheckListService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entry-check-list")
public class EntryCheckListController {

    private final EntryCheckListService service;


    public EntryCheckListController(EntryCheckListService service) {
        this.service = service;
    }

    @PostMapping("/{vehicleId}")
    public ResponseEntity<EntryCheckListResponseDTO> create(@PathVariable UUID vehicleId, @RequestBody @Valid EntryCheckListRequestDTO dto) {
        Command<EntryCheckListResponseDTO> command = new CreateEntryCheckListCommand(service,dto,vehicleId);
        EntryCheckListResponseDTO response = command.execute();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<EntryCheckListResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EntryCheckListRequestDTO dto){
        Command<EntryCheckListResponseDTO> command = new UpdateEntryCheckListCommand(service,dto,id);
        return ResponseEntity.ok(command.execute());
    }

    @GetMapping("/vehicles/{vehicleId}")
    public ResponseEntity<List<EntryCheckListResponseDTO>> listByVehicles(@PathVariable UUID vehicleId){
        Command<List<EntryCheckListResponseDTO>> command = new FindByVehicleEntryCheckListCommand(service,vehicleId);
        return ResponseEntity.ok(command.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryCheckListResponseDTO> findById(@PathVariable Long id){
        Command<EntryCheckListResponseDTO> command = new FindByIdEntryCheckListCommand(service,id);
        return ResponseEntity.ok(command.execute());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Command<Void> command = new DeleteEntryCheckListCommand(service,id);
        command.execute();
        return ResponseEntity.noContent().build();
    }
}

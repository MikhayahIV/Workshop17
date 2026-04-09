package umc.pp.Workshop17.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.commnad.entryCheckList.CreateEntryCheckListCommand;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListRequestDTO;
import umc.pp.Workshop17.dto.services.entryCheckList.EntryCheckListResponseDTO;
import umc.pp.Workshop17.service.services.EntryCheckListService;

import java.util.UUID;

@RestController
@RequestMapping("/entry-check-list")
public class EntryCheckListController {

    private final EntryCheckListService service;

    public EntryCheckListController(EntryCheckListService service) {
        this.service = service;
    }

    @PostMapping("/vehicle/{vehicleId}")
    public ResponseEntity<EntryCheckListResponseDTO> create(@PathVariable UUID vehicleId, @RequestBody @Valid EntryCheckListRequestDTO dto) {
        Command<EntryCheckListResponseDTO> command = new CreateEntryCheckListCommand(service,dto,vehicleId);
        EntryCheckListResponseDTO response = command.execute();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

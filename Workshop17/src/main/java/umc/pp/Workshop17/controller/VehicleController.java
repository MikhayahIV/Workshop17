package umc.pp.Workshop17.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.commnad.vehicle.*;
import umc.pp.Workshop17.dto.vehicle.VehicleRequestDTO;
import umc.pp.Workshop17.dto.vehicle.VehicleResponseDTO;
import umc.pp.Workshop17.service.vehicle.VehicleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> create(@RequestBody @Valid VehicleRequestDTO dto) {
        Command<VehicleResponseDTO> command = new CreateVehicleCommand(service,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(command.execute());
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> findAll() {
        Command<List<VehicleResponseDTO>> command = new FindAllVehicleCommand(service);
        return ResponseEntity.ok(command.execute());
    }

    @GetMapping("/plate/{plate}")
    public ResponseEntity<VehicleResponseDTO> findByPlate(@PathVariable String plate) {
        Command<VehicleResponseDTO> command = new FindByPlateVehicleCommand(service, plate);
        return ResponseEntity.ok(command.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid VehicleRequestDTO dto) {
        Command<VehicleResponseDTO> command = new UpdateVehicleCommand(service, id, dto);
        return ResponseEntity.ok(command.execute());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Command<Void> command = new DeleteVehicleCommand(service, id);
        command.execute();
        return ResponseEntity.noContent().build();
    }
}

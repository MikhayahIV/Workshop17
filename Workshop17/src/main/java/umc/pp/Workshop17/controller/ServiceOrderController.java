package umc.pp.Workshop17.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.pp.Workshop17.commnad.Command;
import umc.pp.Workshop17.commnad.serviceOrder.CancelServiceOrderCommand;
import umc.pp.Workshop17.commnad.serviceOrder.CreateServiceOrderCommand;
import umc.pp.Workshop17.commnad.serviceOrder.FinishServiceOrderCommand;
import umc.pp.Workshop17.commnad.serviceOrder.UpdateServiceOrderCommand;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderRequestDTO;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
import umc.pp.Workshop17.dto.services.ServiceOrder.update.ServiceOrderUpdateDTO;
import umc.pp.Workshop17.service.services.ServiceOrderService;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    private final ServiceOrderService service;

    public ServiceOrderController(ServiceOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponseDTO> create(@RequestBody @Valid ServiceOrderRequestDTO dto) {
        CreateServiceOrderCommand command = new CreateServiceOrderCommand(service, dto);
        ServiceOrderResponseDTO response = command.execute();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/execution")
    public ResponseEntity<ServiceOrderResponseDTO> updateExecution(@PathVariable Long id, @RequestBody @Valid ServiceOrderUpdateDTO dto) {
        Command<ServiceOrderResponseDTO> command = new UpdateServiceOrderCommand(service, id, dto);
        return ResponseEntity.ok(command.execute());
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<ServiceOrderResponseDTO> finish(@PathVariable Long id) {
        Command<ServiceOrderResponseDTO> command = new FinishServiceOrderCommand(service, id);
        return ResponseEntity.ok(command.execute());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<ServiceOrderResponseDTO> cancel(@PathVariable Long id, @RequestParam String reason) {
        Command<ServiceOrderResponseDTO> command = new CancelServiceOrderCommand(service, id, reason);
        return ResponseEntity.ok(command.execute());
    }
}

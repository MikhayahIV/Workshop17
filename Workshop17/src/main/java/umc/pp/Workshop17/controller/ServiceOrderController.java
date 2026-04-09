package umc.pp.Workshop17.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.pp.Workshop17.commnad.serviceOrder.CreateServiceOrderCommand;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderRequestDTO;
import umc.pp.Workshop17.dto.services.ServiceOrder.ServiceOrderResponseDTO;
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
}

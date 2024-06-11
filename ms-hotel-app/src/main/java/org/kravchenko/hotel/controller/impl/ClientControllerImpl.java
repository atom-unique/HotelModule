package org.kravchenko.hotel.controller.impl;

import lombok.RequiredArgsConstructor;
import org.kravchenko.hotel.controller.ClientController;
import org.kravchenko.hotel.service.ClientService;
import org.kravchenko.hotel.service.dto.ClientDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    public ClientDto findClient(Long id) {
        return clientService.findClient(id);
    }

    public List<ServicePriceDto> getClientServices(Long id) {
        return clientService.getClientServices(id);
    }

    public ResponseEntity<String> addClient(ClientDto clientDto) {
        clientService.addClient(clientDto);
        return ResponseEntity.ok("Клиент успешно добавлен!");
    }

    public ResponseEntity<String> deleteClient(Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Клиент успешно удален!");
    }

}

package org.kravchenko.hotel.controller.impl;

import lombok.RequiredArgsConstructor;
import org.kravchenko.hotel.controller.ClientController;
import org.kravchenko.hotel.service.ClientService;
import org.kravchenko.hotel.service.dto.ClientDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
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

    public void addClient(ClientDto clientDto) {
        clientService.addClient(clientDto);
    }

    public void deleteClient(Long id) {
        clientService.deleteClient(id);
    }

}

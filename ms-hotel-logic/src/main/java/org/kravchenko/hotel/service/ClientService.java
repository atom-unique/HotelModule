package org.kravchenko.hotel.service;

import org.kravchenko.hotel.service.dto.ClientDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;

import java.util.List;

public interface ClientService {

    ClientDto findClient(Long id);

    void addClient(ClientDto clientDto);

    void deleteClient(Long id);

    List<ServicePriceDto> getClientServices(Long id);

}

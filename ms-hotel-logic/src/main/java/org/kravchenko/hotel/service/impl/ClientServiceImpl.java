package org.kravchenko.hotel.service.impl;

import lombok.AllArgsConstructor;
import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.repository.ClientRepository;
import org.kravchenko.hotel.service.ClientService;
import org.kravchenko.hotel.service.converter.ClientConverter;
import org.kravchenko.hotel.service.converter.ServicePriceConverter;
import org.kravchenko.hotel.service.dto.ClientDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientConverter clientConverter;

    private final ServicePriceConverter servicePriceConverter;

    @Override
    public ClientDto findClient(Long id) {
        return clientConverter.toDto(clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        ));
    }

    @Override
    public void addClient(ClientDto clientDto) {
        clientRepository.save(clientConverter.toModel(clientDto));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ServicePriceDto> getClientServices(Long id) {
        return clientRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException(id)
                ).getServices().stream()
                .map(servicePriceConverter::toDto)
                .sorted(Comparator.comparing(ServicePriceDto::getPrice))
                .toList();
    }

}

package org.kravchenko.hotel.service.impl;

import lombok.AllArgsConstructor;
import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.model.Service;
import org.kravchenko.hotel.repository.ServiceRepository;
import org.kravchenko.hotel.service.ServiceService;
import org.kravchenko.hotel.service.converter.ServiceConverter;
import org.kravchenko.hotel.service.converter.ServicePriceConverter;
import org.kravchenko.hotel.service.dto.ServiceDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    private final ServiceConverter serviceConverter;

    private final ServicePriceConverter servicePriceConverter;

    @Override
    public void addService(ServiceDto serviceDto) {
        serviceRepository.save(serviceConverter.toModel(serviceDto));
    }

    @Override
    public void servicePriceUpdate(Long id, int price) {
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        );
        service.setPrice(price);
        serviceRepository.save(service);
    }

    @Override
    public List<ServicePriceDto> getAllServicesWithPrice() {
        return serviceRepository.findAll()
                .stream()
                .map(servicePriceConverter::toDto)
                .sorted(Comparator.comparingInt(ServicePriceDto::getPrice))
                .toList();
    }

}

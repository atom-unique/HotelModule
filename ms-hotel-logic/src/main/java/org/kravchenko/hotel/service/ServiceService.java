package org.kravchenko.hotel.service;

import org.kravchenko.hotel.service.dto.ServiceDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;

import java.util.List;

public interface ServiceService {

    void addService(ServiceDto serviceDto);

    void servicePriceUpdate(Long id, int price);

    List<ServicePriceDto> getAllServicesWithPrice();

}

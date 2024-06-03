package org.kravchenko.hotel.service.impl;

import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.model.Service;
import org.kravchenko.hotel.repository.ServiceRepository;
import org.kravchenko.hotel.service.converter.ServiceConverter;
import org.kravchenko.hotel.service.converter.ServicePriceConverter;
import org.kravchenko.hotel.service.dto.ServiceDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

@Listeners(MockitoTestNGListener.class)
public class ServiceServiceImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceConverter serviceConverter;

    @Mock
    private ServicePriceConverter servicePriceConverter;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    @Test
    public void addService_ValidServiceDto_SavesService() {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(1L);
        serviceDto.setName("Name");
        serviceDto.setDescription("Description");

        Service service = new Service();
        service.setId(1L);
        service.setName("Name");
        service.setDescription("Description");

        when(serviceConverter.toModel(serviceDto)).thenReturn(service);

        serviceService.addService(serviceDto);

        verify(serviceConverter, times(1)).toModel(serviceDto);
        verify(serviceRepository, times(1)).save(service);
    }

    @Test
    public void servicePriceUpdate_ServiceFound_UpdatesPrice() {
        Long serviceId = 1L;
        int newPrice = 100;

        Service service = new Service();
        service.setId(serviceId);
        service.setPrice(50);

        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));

        serviceService.servicePriceUpdate(serviceId, newPrice);

        assertEquals(service.getPrice(), newPrice);
        verify(serviceRepository, times(1)).findById(serviceId);
        verify(serviceRepository, times(1)).save(service);
    }

    @Test
    public void servicePriceUpdate_ServiceNotFound_ThrowsEntityNotFoundException() {
        Long serviceId = 1L;
        int newPrice = 100;

        when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());

        try {
            serviceService.servicePriceUpdate(serviceId, newPrice);
            fail("Expected EntityNotFoundException to be thrown");
        } catch (EntityNotFoundException e) {
            assertEquals(e.getMessage(), "Entity not found by parameter : " + serviceId);
        }

        verify(serviceRepository, times(1)).findById(serviceId);
    }

    @Test
    public void getAllServicesWithPrice_ReturnsSortedServicePriceDtos() {
        Service service1 = new Service();
        Service service2 = new Service();
        service1.setPrice(100);
        service2.setPrice(50);

        ServicePriceDto dto1 = new ServicePriceDto();
        ServicePriceDto dto2 = new ServicePriceDto();
        dto1.setPrice(100);
        dto2.setPrice(50);

        when(serviceRepository.findAll()).thenReturn(List.of(service1, service2));
        when(servicePriceConverter.toDto(service1)).thenReturn(dto1);
        when(servicePriceConverter.toDto(service2)).thenReturn(dto2);

        List<ServicePriceDto> result = serviceService.getAllServicesWithPrice();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getPrice(), 50);
        assertEquals(result.get(1).getPrice(), 100);
        verify(serviceRepository, times(1)).findAll();
        verify(servicePriceConverter, times(1)).toDto(service1);
        verify(servicePriceConverter, times(1)).toDto(service2);
    }

}
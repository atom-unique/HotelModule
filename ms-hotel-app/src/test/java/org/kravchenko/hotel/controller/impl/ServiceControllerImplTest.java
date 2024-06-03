package org.kravchenko.hotel.controller.impl;

import org.kravchenko.hotel.service.ServiceService;
import org.kravchenko.hotel.service.dto.ServiceDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Listeners(MockitoTestNGListener.class)
public class ServiceControllerImplTest {

    @Mock
    private ServiceService service;

    @InjectMocks
    private ServiceControllerImpl serviceController;

    @Test
    public void getAllServicesWithPrice_ReturnsListOfServicePriceDto() {
        ServicePriceDto service1 = new ServicePriceDto();
        ServicePriceDto service2 = new ServicePriceDto();
        List<ServicePriceDto> services = Arrays.asList(service1, service2);

        when(service.getAllServicesWithPrice()).thenReturn(services);

        List<ServicePriceDto> result = serviceController.getAllServicesWithPrice();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(service, times(1)).getAllServicesWithPrice();
    }

    @Test
    public void addService_ValidServiceDto_AddsService() {
        ServiceDto serviceDto = new ServiceDto();

        serviceController.addService(serviceDto);

        verify(service, times(1)).addService(serviceDto);
    }

    @Test
    public void servicePriceUpdate_ValidIdAndPrice_UpdatesServicePrice() {
        Long serviceId = 1L;
        int price = 100;

        serviceController.servicePriceUpdate(serviceId, price);

        verify(service, times(1)).servicePriceUpdate(serviceId, price);
    }

}
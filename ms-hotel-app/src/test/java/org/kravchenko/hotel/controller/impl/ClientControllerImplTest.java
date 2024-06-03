package org.kravchenko.hotel.controller.impl;

import org.kravchenko.hotel.service.ClientService;
import org.kravchenko.hotel.service.dto.ClientDto;
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
public class ClientControllerImplTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientControllerImpl clientController;

    @Test
    public void findClient_ValidId_ReturnsClientDto() {
        Long clientId = 1L;
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientId);

        when(clientService.findClient(clientId)).thenReturn(clientDto);

        ClientDto result = clientController.findClient(clientId);

        assertNotNull(result);
        assertEquals(result.getId(), clientId);
        verify(clientService, times(1)).findClient(clientId);
    }

    @Test
    public void getClientServices_ValidId_ReturnsServicePriceDtos() {
        Long clientId = 1L;
        ServicePriceDto service1 = new ServicePriceDto();
        ServicePriceDto service2 = new ServicePriceDto();
        List<ServicePriceDto> services = Arrays.asList(service1, service2);

        when(clientService.getClientServices(clientId)).thenReturn(services);

        List<ServicePriceDto> result = clientController.getClientServices(clientId);

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(clientService, times(1)).getClientServices(clientId);
    }

    @Test
    public void addClient_ValidClientDto_AddsClient() {
        ClientDto clientDto = new ClientDto();

        clientController.addClient(clientDto);

        verify(clientService, times(1)).addClient(clientDto);
    }

    @Test
    public void deleteClient_ValidId_DeletesClient() {
        Long clientId = 1L;

        clientController.deleteClient(clientId);

        verify(clientService, times(1)).deleteClient(clientId);
    }

}
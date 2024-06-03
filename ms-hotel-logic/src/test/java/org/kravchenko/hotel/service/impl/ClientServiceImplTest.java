package org.kravchenko.hotel.service.impl;

import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.model.Client;
import org.kravchenko.hotel.model.Service;
import org.kravchenko.hotel.repository.ClientRepository;
import org.kravchenko.hotel.service.converter.ClientConverter;
import org.kravchenko.hotel.service.converter.ServicePriceConverter;
import org.kravchenko.hotel.service.dto.ClientDto;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Listeners({
        MockitoTestNGListener.class
})
public class ClientServiceImplTest {

    @Spy
    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    ClientConverter clientConverter;

    @Mock
    ServicePriceConverter servicePriceConverter;

    private Client client;
    private ClientDto clientDto;

    @BeforeMethod
    public void setUp() {
        client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");

        clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("Name");
        clientDto.setSurname("Surname");
    }

    @Test
    public void findClient_ClientFound_ReturnsClientDto() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientConverter.toDto(client)).thenReturn(clientDto);

        ClientDto result = clientService.findClient(1L);

        assertNotNull(result);
        assertEquals(clientDto.getId(), result.getId());
        verify(clientRepository, times(1)).findById(1L);
        verify(clientConverter, times(1)).toDto(client);
    }

    @Test
    public void findClient_ClientNotFound_ThrowsEntityNotFoundException() {
        Long id = 1L;
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        try {
            clientService.findClient(id);
        } catch (EntityNotFoundException e) {
            assertEquals("Entity not found by parameter : " + id, e.getMessage());
        }

        verify(clientRepository, times(1)).findById(1L);
        verify(clientConverter, never()).toDto(any());
    }

    @Test
    public void addClient_ValidClientDto_SavesClient() {
        when(clientConverter.toModel(clientDto)).thenReturn(client);

        clientService.addClient(clientDto);

        verify(clientConverter, times(1)).toModel(clientDto);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void deleteClient_ValidId_DeletesClient() {
        Long clientId = 1L;

        clientService.deleteClient(clientId);

        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    public void getClientServices_ClientFound_ReturnsSortedServicePriceDtos() {
        Long clientId = 1L;

        Client client = new Client();
        Service service1 = new Service();
        service1.setPrice(100);

        Service service2 = new Service();
        service2.setPrice(50);

        List<Service> services = Arrays.asList(service1, service2);
        client.setServices(services);

        ServicePriceDto servicePriceDto1 = new ServicePriceDto();
        servicePriceDto1.setPrice(100);

        ServicePriceDto servicePriceDto2 = new ServicePriceDto();
        servicePriceDto2.setPrice(50);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(servicePriceConverter.toDto(service1)).thenReturn(servicePriceDto1);
        when(servicePriceConverter.toDto(service2)).thenReturn(servicePriceDto2);

        List<ServicePriceDto> result = clientService.getClientServices(clientId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(50.0, result.get(0).getPrice());
        assertEquals(100.0, result.get(1).getPrice());

        verify(clientRepository, times(1)).findById(clientId);
        verify(servicePriceConverter, times(1)).toDto(service1);
        verify(servicePriceConverter, times(1)).toDto(service2);
    }

    @Test
    public void getClientServices_ClientNotFound_ThrowsEntityNotFoundException() {
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        try {
            clientService.getClientServices(clientId);
        } catch (EntityNotFoundException e) {
            assertEquals("Entity not found by parameter : " + clientId, e.getMessage());
        }

        verify(clientRepository, times(1)).findById(clientId);
    }

}
package org.kravchenko.hotel.controller.impl;

import org.kravchenko.hotel.service.RecordService;
import org.kravchenko.hotel.service.dto.RecordClientDateDto;
import org.kravchenko.hotel.service.dto.RecordClientWithRoomDto;
import org.kravchenko.hotel.service.dto.RoomNoClientsDto;
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
public class RecordControllerImplTest {

    @Mock
    private RecordService recordService;

    @InjectMocks
    private RecordControllerImpl recordController;

    @Test
    public void checkIn_ValidParameters_CallsServiceMethod() {
        Long clientId = 1L;
        Long employeeId = 2L;
        Long roomId = 3L;
        String checkOutDate = "2024-06-20";
        List<Long> services = Arrays.asList(1L, 2L, 3L);

        recordController.checkIn(clientId, employeeId, roomId, checkOutDate, services);

        verify(recordService, times(1))
                .checkIn(clientId, employeeId, roomId, checkOutDate, services);
    }

    @Test
    public void checkOut_ValidParameters_CallsServiceMethod() {
        String name = "Name";
        String surname = "Surname";

        recordController.checkOut(name, surname);

        verify(recordService, times(1)).checkOut(name, surname);
    }

    @Test
    public void getAllFreeRoomsCount_ReturnsCorrectCount() {
        int expectedCount = 5;
        when(recordService.clientsCount()).thenReturn(expectedCount);

        int result = recordController.getClientsCount();

        assertEquals(result, expectedCount);
        verify(recordService, times(1)).clientsCount();
    }

    @Test
    public void getClientsWithRooms_ReturnsListOfClientsWithRooms() {
        RecordClientWithRoomDto client1 = new RecordClientWithRoomDto();
        RecordClientWithRoomDto client2 = new RecordClientWithRoomDto();
        List<RecordClientWithRoomDto> clients = Arrays.asList(client1, client2);

        when(recordService.getClientsWithRooms()).thenReturn(clients);

        List<RecordClientWithRoomDto> result = recordController.getClientsWithRooms();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(recordService, times(1)).getClientsWithRooms();
    }

    @Test
    public void getClientsWithRoomsByCheckOut_ReturnsListOfClientsWithRooms() {
        RecordClientWithRoomDto client1 = new RecordClientWithRoomDto();
        RecordClientWithRoomDto client2 = new RecordClientWithRoomDto();
        List<RecordClientWithRoomDto> clients = Arrays.asList(client1, client2);

        when(recordService.getClientsWithRoomsByCheckOut()).thenReturn(clients);

        List<RecordClientWithRoomDto> result = recordController.getClientsWithRoomsByCheckOut();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(recordService, times(1)).getClientsWithRoomsByCheckOut();
    }

    @Test
    public void getTotalForRoom_ValidId_ReturnsTotal() {
        Long roomId = 1L;
        int expectedTotal = 100;

        when(recordService.getTotalForRoom(roomId)).thenReturn(expectedTotal);

        int result = recordController.getTotalForRoom(roomId);

        assertEquals(result, expectedTotal);
        verify(recordService, times(1)).getTotalForRoom(roomId);
    }

    @Test
    public void getLastRoomClients_ValidRoomId_ReturnsListOfClients() {
        Long roomId = 1L;
        RecordClientDateDto client1 = new RecordClientDateDto();
        RecordClientDateDto client2 = new RecordClientDateDto();
        List<RecordClientDateDto> clients = Arrays.asList(client1, client2);

        when(recordService.getLastRoomClients(roomId)).thenReturn(clients);

        List<RecordClientDateDto> result = recordController.getLastRoomClients(roomId);

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(recordService, times(1)).getLastRoomClients(roomId);
    }

    @Test
    public void getAllFreeRoomsByDate_ValidDate_ReturnsListOfFreeRooms() {
        String dateLine = "2024-06-20";
        RoomNoClientsDto room1 = new RoomNoClientsDto();
        RoomNoClientsDto room2 = new RoomNoClientsDto();
        List<RoomNoClientsDto> rooms = Arrays.asList(room1, room2);

        when(recordService.getAllFreeRoomsByDate(dateLine)).thenReturn(rooms);

        List<RoomNoClientsDto> result = recordController.getAllFreeRoomsByDate(dateLine);

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(recordService, times(1)).getAllFreeRoomsByDate(dateLine);
    }

}
package org.kravchenko.hotel.controller.impl;

import org.kravchenko.hotel.service.RoomService;
import org.kravchenko.hotel.service.dto.RoomDto;
import org.kravchenko.hotel.service.dto.RoomPriceDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeClass;
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
public class RoomControllerImplTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomControllerImpl roomController;

    private final RoomDto room1 = new RoomDto();

    private final RoomDto room2 = new RoomDto();

    private List<RoomDto> rooms;

    @BeforeClass
    private void setUp() {
        this.rooms = Arrays.asList(room1, room2);
    }

    @Test
    public void findRoom_ValidId_ReturnsRoomDto() {
        Long roomId = 1L;
        RoomDto roomDto = new RoomDto();
        roomDto.setId(roomId);

        when(roomService.findRoom(roomId)).thenReturn(roomDto);

        RoomDto result = roomController.findRoom(roomId);

        assertNotNull(result);
        assertEquals(result.getId(), roomId);
        verify(roomService, times(1)).findRoom(roomId);
    }

    @Test
    public void getAllRoomsWithPrice_ReturnsListOfRoomPriceDto() {
        RoomPriceDto room1 = new RoomPriceDto();
        RoomPriceDto room2 = new RoomPriceDto();
        List<RoomPriceDto> rooms = Arrays.asList(room1, room2);

        when(roomService.getAllRoomsWithPrice()).thenReturn(rooms);

        List<RoomPriceDto> result = roomController.getAllRoomsWithPrice();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(roomService, times(1)).getAllRoomsWithPrice();
    }

    @Test
    public void findAllRoomsSortedByPrice_ReturnsListOfRoomDto() {
        when(roomService.findAllRoomsSortedByPrice()).thenReturn(rooms);

        List<RoomDto> result = roomController.findAllRoomsSortedByPrice();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(roomService, times(1)).findAllRoomsSortedByPrice();
    }

    @Test
    public void findAllRoomsSortedByCapacity_ReturnsListOfRoomDto() {
        when(roomService.findAllRoomsSortedByCapacity()).thenReturn(rooms);

        List<RoomDto> result = roomController.findAllRoomsSortedByCapacity();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(roomService, times(1)).findAllRoomsSortedByCapacity();
    }

    @Test
    public void findAllRoomsSortedByRating_ReturnsListOfRoomDto() {
        when(roomService.findAllRoomsSortedByRating()).thenReturn(rooms);

        List<RoomDto> result = roomController.findAllRoomsSortedByRating();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(roomService, times(1)).findAllRoomsSortedByRating();
    }

    @Test
    public void findAllFreeRoomsSortedByPrice_ReturnsListOfRoomDto() {
        when(roomService.findAllFreeRoomsSortedByPrice()).thenReturn(rooms);

        List<RoomDto> result = roomController.findAllFreeRoomsSortedByPrice();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(roomService, times(1)).findAllFreeRoomsSortedByPrice();
    }

    @Test
    public void findAllFreeRoomsSortedByCapacity_ReturnsListOfRoomDto() {
        when(roomService.findAllFreeRoomsSortedByCapacity()).thenReturn(rooms);

        List<RoomDto> result = roomController.findAllFreeRoomsSortedByCapacity();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(roomService, times(1)).findAllFreeRoomsSortedByCapacity();
    }

    @Test
    public void findAllFreeRoomsSortedByRating_ReturnsListOfRoomDto() {
        when(roomService.findAllFreeRoomsSortedByRating()).thenReturn(rooms);

        List<RoomDto> result = roomController.findAllFreeRoomsSortedByRating();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        verify(roomService, times(1)).findAllFreeRoomsSortedByRating();
    }

    @Test
    public void addRoom_ValidRoomDto_AddsRoom() {
        roomController.addRoom(room1);

        verify(roomService, times(1)).addRoom(room1);
    }

    @Test
    public void roomPriceUpdate_ValidIdAndPrice_UpdatesRoomPrice() {
        Long roomId = 1L;
        int price = 100;

        roomController.roomPriceUpdate(roomId, price);

        verify(roomService, times(1)).roomPriceUpdate(roomId, price);
    }

    @Test
    public void roomStatusUpdate_ValidIdAndStatus_UpdatesRoomStatus() {
        Long roomId = 1L;
        boolean inService = true;

        roomController.roomStatusUpdate(roomId, inService);

        verify(roomService, times(1)).roomStatusUpdate(roomId, inService);
    }

    @Test
    public void getAllFreeRoomsCount_ReturnsCorrectCount() {
        int expectedCount = 5;
        when(roomService.getAllFreeRoomsCount()).thenReturn(expectedCount);

        int result = roomController.getAllFreeRoomsCount();

        assertEquals(result, expectedCount);
        verify(roomService, times(1)).getAllFreeRoomsCount();
    }

}
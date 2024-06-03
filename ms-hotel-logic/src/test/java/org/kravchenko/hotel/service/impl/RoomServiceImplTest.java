package org.kravchenko.hotel.service.impl;

import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.model.Room;
import org.kravchenko.hotel.repository.RoomRepository;
import org.kravchenko.hotel.service.converter.RoomConverter;
import org.kravchenko.hotel.service.converter.RoomPriceConverter;
import org.kravchenko.hotel.service.dto.RoomDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeClass;
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
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomConverter roomConverter;

    @Mock
    private RoomPriceConverter roomPriceConverter;

    @InjectMocks
    private RoomServiceImpl roomService;

    private final Room room1 = new Room();

    private final Room room2 = new Room();

    private final RoomDto dto1 = new RoomDto();

    private final RoomDto dto2 = new RoomDto();

    @BeforeClass
    void setUp() {
        this.room1.setId(1L);
        this.room1.setPrice(100);
        this.room1.setCapacity(3);
        this.room1.setRating(4);
        this.room1.setOccupied(false);

        this.room2.setId(1L);
        this.room2.setPrice(50);
        this.room2.setCapacity(2);
        this.room2.setRating(3);
        this.room2.setOccupied(false);

        this.dto1.setId(1L);
        this.dto1.setPrice(100);
        this.dto1.setCapacity(3);
        this.dto1.setRating(4);

        this.dto2.setId(1L);
        this.dto2.setPrice(50);
        this.dto2.setCapacity(2);
        this.dto2.setRating(3);
    }

    @Test
    public void addRoom_ValidRoomDto_SavesRoom() {
        when(roomConverter.toModel(dto1)).thenReturn(room1);

        roomService.addRoom(dto1);

        verify(roomConverter, times(1)).toModel(dto1);
        verify(roomRepository, times(1)).save(room1);
    }

    @Test
    public void findRoom_RoomFound_ReturnsRoomDto() {
        Long roomId = 1L;

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room1));
        when(roomConverter.toDto(room1)).thenReturn(dto1);

        RoomDto result = roomService.findRoom(roomId);

        assertNotNull(result);
        assertEquals(result.getId(), roomId);
        verify(roomRepository, times(1)).findById(roomId);
        verify(roomConverter, times(1)).toDto(room1);
    }

    @Test
    public void findRoom_RoomNotFound_ThrowsEntityNotFoundException() {
        Long roomId = 1L;

        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        try {
            roomService.findRoom(roomId);
            fail("Expected EntityNotFoundException to be thrown");
        } catch (EntityNotFoundException e) {
            assertEquals(e.getMessage(), "Entity not found by parameter : " + roomId);
        }

        verify(roomRepository, times(1)).findById(roomId);
    }

    @Test
    public void findAllRoomsSortedByPrice_ReturnsSortedRoomDtos() {
        when(roomRepository.findAll()).thenReturn(List.of(room1, room2));
        when(roomConverter.toDto(room1)).thenReturn(dto1);
        when(roomConverter.toDto(room2)).thenReturn(dto2);

        List<RoomDto> result = roomService.findAllRoomsSortedByPrice();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getPrice(), 50);
        assertEquals(result.get(1).getPrice(), 100);
        verify(roomRepository, times(1)).findAll();
        verify(roomConverter, times(1)).toDto(room1);
        verify(roomConverter, times(1)).toDto(room2);
    }

    @Test
    public void findAllRoomsSortedByCapacity_ReturnsSortedRoomDtos() {
        when(roomRepository.findAll()).thenReturn(List.of(room1, room2));
        when(roomConverter.toDto(room1)).thenReturn(dto1);
        when(roomConverter.toDto(room2)).thenReturn(dto2);

        List<RoomDto> result = roomService.findAllRoomsSortedByCapacity();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getCapacity(), 2);
        assertEquals(result.get(1).getCapacity(), 3);
        verify(roomRepository, times(1)).findAll();
        verify(roomConverter, times(1)).toDto(room1);
        verify(roomConverter, times(1)).toDto(room2);
    }

    @Test
    public void findAllRoomsSortedByRating_ReturnsSortedRoomDtos() {
        when(roomRepository.findAll()).thenReturn(List.of(room1, room2));
        when(roomConverter.toDto(room1)).thenReturn(dto1);
        when(roomConverter.toDto(room2)).thenReturn(dto2);

        List<RoomDto> result = roomService.findAllRoomsSortedByRating();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getRating(), 3);
        assertEquals(result.get(1).getRating(), 4);
        verify(roomRepository, times(1)).findAll();
        verify(roomConverter, times(1)).toDto(room1);
        verify(roomConverter, times(1)).toDto(room2);
    }

    @Test
    public void findAllFreeRoomsSortedByPrice_ReturnsSortedRoomDtos() {
        when(roomRepository.findAllByIsOccupiedIsFalse()).thenReturn(List.of(room1, room2));
        when(roomConverter.toDto(room1)).thenReturn(dto1);
        when(roomConverter.toDto(room2)).thenReturn(dto2);

        List<RoomDto> result = roomService.findAllFreeRoomsSortedByPrice();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getPrice(), 50);
        assertEquals(result.get(1).getPrice(), 100);
        verify(roomRepository, times(1)).findAllByIsOccupiedIsFalse();
        verify(roomConverter, times(1)).toDto(room1);
        verify(roomConverter, times(1)).toDto(room2);
    }

    @Test
    public void findAllFreeRoomsSortedByCapacity_ReturnsSortedRoomDtos() {
        when(roomRepository.findAllByIsOccupiedIsFalse()).thenReturn(List.of(room1, room2));
        when(roomConverter.toDto(room1)).thenReturn(dto1);
        when(roomConverter.toDto(room2)).thenReturn(dto2);

        List<RoomDto> result = roomService.findAllFreeRoomsSortedByCapacity();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getCapacity(), 2);
        assertEquals(result.get(1).getCapacity(), 3);
        verify(roomRepository, times(1)).findAllByIsOccupiedIsFalse();
        verify(roomConverter, times(1)).toDto(room1);
        verify(roomConverter, times(1)).toDto(room2);
    }

}
package org.kravchenko.hotel.service;

import org.kravchenko.hotel.service.dto.RoomDto;
import org.kravchenko.hotel.service.dto.RoomPriceDto;

import java.util.List;

public interface RoomService {

    void addRoom(RoomDto roomDto);

    RoomDto findRoom(Long id);

    List<RoomDto> findAllRoomsSortedByPrice();

    List<RoomDto> findAllRoomsSortedByCapacity();

    List<RoomDto> findAllRoomsSortedByRating();

    List<RoomDto> findAllFreeRoomsSortedByPrice();

    List<RoomDto> findAllFreeRoomsSortedByCapacity();

    List<RoomDto> findAllFreeRoomsSortedByRating();

    void roomPriceUpdate(Long id, int price);

    void roomStatusUpdate(Long id, boolean inService);

    int getAllFreeRoomsCount();

    List<RoomPriceDto> getAllRoomsWithPrice();

}

package org.kravchenko.hotel.controller.impl;

import lombok.RequiredArgsConstructor;
import org.kravchenko.hotel.controller.RoomController;
import org.kravchenko.hotel.service.RoomService;
import org.kravchenko.hotel.service.dto.RoomDto;
import org.kravchenko.hotel.service.dto.RoomPriceDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomControllerImpl implements RoomController {

    private final RoomService roomService;

    public RoomDto findRoom(Long id) {
        return roomService.findRoom(id);
    }

    public List<RoomPriceDto> getAllRoomsWithPrice() {
        return roomService.getAllRoomsWithPrice();
    }

    public List<RoomDto> findAllRoomsSortedByPrice() {
        return roomService.findAllRoomsSortedByPrice();
    }

    public List<RoomDto> findAllRoomsSortedByCapacity() {
        return roomService.findAllRoomsSortedByCapacity();
    }

    public List<RoomDto> findAllRoomsSortedByRating() {
        return roomService.findAllRoomsSortedByRating();
    }

    public List<RoomDto> findAllFreeRoomsSortedByPrice() {
        return roomService.findAllFreeRoomsSortedByPrice();
    }

    public List<RoomDto> findAllFreeRoomsSortedByCapacity() {
        return roomService.findAllFreeRoomsSortedByCapacity();
    }

    public List<RoomDto> findAllFreeRoomsSortedByRating() {
        return roomService.findAllFreeRoomsSortedByRating();
    }

    public void addRoom(RoomDto roomDto) {
        roomService.addRoom(roomDto);
    }

    public void roomPriceUpdate(Long id, int price) {
        roomService.roomPriceUpdate(id, price);
    }

    public void roomStatusUpdate(Long id, boolean inService) {
        roomService.roomStatusUpdate(id, inService);
    }

    public int getAllFreeRoomsCount() {
        return roomService.getAllFreeRoomsCount();
    }

}

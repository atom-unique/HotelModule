package org.kravchenko.hotel.controller.impl;

import lombok.RequiredArgsConstructor;
import org.kravchenko.hotel.controller.RecordController;
import org.kravchenko.hotel.service.RecordService;
import org.kravchenko.hotel.service.dto.RecordClientDateDto;
import org.kravchenko.hotel.service.dto.RecordClientWithRoomDto;
import org.kravchenko.hotel.service.dto.RoomNoClientsDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RecordControllerImpl implements RecordController {

    private final RecordService recordService;

    public void checkIn(
            Long clientId,
            Long employeeId,
            Long roomId,
            String checkOutDate,
            List<Long> services
    ) {
        recordService.checkIn(clientId, employeeId, roomId, checkOutDate, services);
    }

    public void checkOut(String name, String surname) {
        recordService.checkOut(name, surname);
    }

    public int getAllFreeRoomsCount() {
        return recordService.clientsCount();
    }

    public List<RecordClientWithRoomDto> getClientsWithRooms() {
        return recordService.getClientsWithRooms();
    }

    public List<RecordClientWithRoomDto> getClientsWithRoomsByCheckOut() {
        return recordService.getClientsWithRoomsByCheckOut();
    }

    public int getTotalForRoom(Long id) {
        return recordService.getTotalForRoom(id);
    }

    public List<RecordClientDateDto> getLastRoomClients(Long roomId) {
        return recordService.getLastRoomClients(roomId);
    }

    public List<RoomNoClientsDto> getAllFreeRoomsByDate(String dateLine) {
        return recordService.getAllFreeRoomsByDate(dateLine);
    }

}

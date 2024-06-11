package org.kravchenko.hotel.controller.impl;

import lombok.RequiredArgsConstructor;
import org.kravchenko.hotel.controller.RecordController;
import org.kravchenko.hotel.service.RecordService;
import org.kravchenko.hotel.service.dto.RecordClientDateDto;
import org.kravchenko.hotel.service.dto.RecordClientWithRoomDto;
import org.kravchenko.hotel.service.dto.RoomNoClientsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RecordControllerImpl implements RecordController {

    private final RecordService recordService;

    public ResponseEntity<String> checkIn(
            Long clientId,
            Long employeeId,
            Long roomId,
            String checkOutDate,
            List<Long> services
    ) {
        recordService.checkIn(clientId, employeeId, roomId, checkOutDate, services);
        return ResponseEntity.ok("Клиент успешно заселен!");
    }

    public ResponseEntity<String> checkOut(String name, String surname) {
        recordService.checkOut(name, surname);
        return ResponseEntity.ok("Клиент выселен!");
    }

    public int getClientsCount() {
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

    public List<RecordClientDateDto> getLastRoomClients(Long id) {
        return recordService.getLastRoomClients(id);
    }

    public List<RoomNoClientsDto> getAllFreeRoomsByDate(String dateLine) {
        return recordService.getAllFreeRoomsByDate(dateLine);
    }

}

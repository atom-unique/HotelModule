package org.kravchenko.hotel.service;

import org.kravchenko.hotel.service.dto.RecordClientDateDto;
import org.kravchenko.hotel.service.dto.RecordClientWithRoomDto;
import org.kravchenko.hotel.service.dto.RoomNoClientsDto;

import java.util.List;

public interface RecordService {

    void checkIn(Long clientId, Long employeeId, Long roomId, String checkOutDate, List<Long> services);

    void checkOut(String name, String surname);

    int clientsCount();

    List<RecordClientWithRoomDto> getClientsWithRooms();

    List<RecordClientWithRoomDto> getClientsWithRoomsByCheckOut();

    int getTotalForRoom(Long id);

    List<RecordClientDateDto> getLastRoomClients(Long roomId);

    List<RoomNoClientsDto> getAllFreeRoomsByDate(String dateLine);

}

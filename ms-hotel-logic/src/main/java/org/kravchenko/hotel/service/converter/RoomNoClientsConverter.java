package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Room;
import org.kravchenko.hotel.service.dto.RoomNoClientsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomNoClientsConverter {

    RoomNoClientsDto toDto(Room room);

}

package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Room;
import org.kravchenko.hotel.service.dto.RoomDto;
import org.mapstruct.Mapper;

@Mapper(uses = ClientConverter.class, componentModel = "spring")
public interface RoomConverter {

    Room toModel(RoomDto roomDto);

    RoomDto toDto(Room room);

}

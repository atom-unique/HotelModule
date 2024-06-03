package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Room;
import org.kravchenko.hotel.service.dto.RoomPriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomPriceConverter {

    RoomPriceDto toDto(Room room);

}

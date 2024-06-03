package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Record;
import org.kravchenko.hotel.service.dto.RecordClientWithRoomDto;
import org.mapstruct.Mapper;

@Mapper(uses = ClientConverter.class, componentModel = "spring")
public interface RecordClientWithRoomConverter {

    RecordClientWithRoomDto toDto(Record record);

}

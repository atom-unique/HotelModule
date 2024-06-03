package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Record;
import org.kravchenko.hotel.service.dto.RecordDto;
import org.mapstruct.Mapper;

@Mapper(uses = {RoomConverter.class, ClientConverter.class, EmployeeConverter.class}, componentModel = "spring")
public interface RecordConverter {

    Record toModel(RecordDto recordDto);

    RecordDto toDto(Record record);

}

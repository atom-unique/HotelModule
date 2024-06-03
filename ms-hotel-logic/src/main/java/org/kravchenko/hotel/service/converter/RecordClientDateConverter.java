package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Record;
import org.kravchenko.hotel.service.dto.RecordClientDateDto;
import org.mapstruct.Mapper;

@Mapper(uses = {ClientConverter.class, ClientNoServicesConverter.class}, componentModel = "spring")
public interface RecordClientDateConverter {

    RecordClientDateDto toDto(Record record);

}

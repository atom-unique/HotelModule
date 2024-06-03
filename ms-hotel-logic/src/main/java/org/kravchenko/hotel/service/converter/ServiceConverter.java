package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Service;
import org.kravchenko.hotel.service.dto.ServiceDto;
import org.mapstruct.Mapper;

@Mapper(uses = ClientConverter.class, componentModel = "spring")
public interface ServiceConverter {

    Service toModel(ServiceDto serviceDto);

    ServiceDto toDto(Service service);

}

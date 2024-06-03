package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Service;
import org.kravchenko.hotel.service.dto.ServicePriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicePriceConverter {

    ServicePriceDto toDto(Service service);

}

package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Client;
import org.kravchenko.hotel.service.dto.ClientNoServicesDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientNoServicesConverter {

    ClientNoServicesDto toDto(Client client);

}

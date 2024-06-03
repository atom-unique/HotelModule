package org.kravchenko.hotel.service.converter;

import org.kravchenko.hotel.model.Client;
import org.kravchenko.hotel.service.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(uses = ServiceConverter.class, componentModel = "spring")
public interface ClientConverter {

    Client toModel(ClientDto clientDto);

    ClientDto toDto(Client client);

}

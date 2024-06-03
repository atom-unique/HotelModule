package org.kravchenko.hotel.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityNotFoundResponse {

    private String errorMessage;

}

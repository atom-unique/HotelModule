package org.kravchenko.hotel.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomIsInServiceResponse {

    private String errorMessage;

}

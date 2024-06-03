package org.kravchenko.hotel.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NullPointerResponse {

    private String errorMessage;

}

package org.kravchenko.hotel.exception;

public class RoomIsInServiceException extends RuntimeException {

    public RoomIsInServiceException(Object parameter) {
        super("The room : " + parameter + " is in service.");
    }

}

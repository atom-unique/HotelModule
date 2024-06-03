package org.kravchenko.hotel.exception;

public class NoAvailablePlacesException extends RuntimeException {

    public NoAvailablePlacesException(Object parameter) {
        super("No available places in the room : " + parameter);
    }

}

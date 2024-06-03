package org.kravchenko.hotel.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Object parameter) {
        super("Entity not found by parameter : " + parameter);
    }

}
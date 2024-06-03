package org.kravchenko.hotel.exception.handler;

import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.exception.NoAvailablePlacesException;
import org.kravchenko.hotel.exception.RoomIsInServiceException;
import org.kravchenko.hotel.exception.response.EntityNotFoundResponse;
import org.kravchenko.hotel.exception.response.NoAvailablePlacesResponse;
import org.kravchenko.hotel.exception.response.NullPointerResponse;
import org.kravchenko.hotel.exception.response.RoomIsInServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    private ResponseEntity<NoAvailablePlacesResponse> handlerException(NoAvailablePlacesException e) {
        NoAvailablePlacesResponse response = new NoAvailablePlacesResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<EntityNotFoundResponse> handlerException(EntityNotFoundException e) {
        EntityNotFoundResponse response = new EntityNotFoundResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<NullPointerResponse> handlerException(NullPointerException e) {
        NullPointerResponse response = new NullPointerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<RoomIsInServiceResponse> handlerException(RoomIsInServiceException e) {
        RoomIsInServiceResponse response = new RoomIsInServiceResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

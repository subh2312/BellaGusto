package org.subhankar.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.subhankar.restaurant.model.DTO.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Result> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(Result.builder().message(e.getMessage()).code("FDAE-0000").data(null).build(),HttpStatus.NOT_FOUND);
    }
}

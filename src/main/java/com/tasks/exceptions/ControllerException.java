package com.tasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<ErrorResponse> handlerTaskNotFound(TaskNotFound taskNotFound) {
        return new ResponseEntity<>(new ErrorResponse(404, taskNotFound.getMessage()), HttpStatus.NOT_FOUND);
    }
}

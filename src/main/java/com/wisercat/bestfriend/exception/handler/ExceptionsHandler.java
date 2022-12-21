package com.wisercat.bestfriend.exception.handler;

import com.wisercat.bestfriend.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseEntity onException(NotFoundException exception) {
        return new ErrorResponseEntity(
                exception.getClass().getSimpleName(),
                exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private String onException(Exception exception) {
        return "internal error!";
    }
}

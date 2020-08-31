package com.example.mobileappws.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException (Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleAnyException (NullPointerException npe, WebRequest request) {
        String errorMessage = (npe.getLocalizedMessage() == null)? npe.toString() : npe.getLocalizedMessage();
        UserException _usex = new UserException( new Date(), errorMessage);
        return new ResponseEntity<>(
                _usex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package com.pmp.kafka_producer_service.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity<>(new Error(LocalDateTime.now(), ex.getMessage(), request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MessageNotSendException.class)
    public final ResponseEntity<Error> handleMessageNotSentException(Exception ex, WebRequest request)
            throws Exception {
        return new ResponseEntity<>(new Error(LocalDateTime.now(), ex.getMessage(), request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

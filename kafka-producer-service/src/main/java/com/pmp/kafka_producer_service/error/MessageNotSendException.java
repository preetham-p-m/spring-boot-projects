package com.pmp.kafka_producer_service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class MessageNotSendException extends RuntimeException {

    public MessageNotSendException(Exception exception) {
        super(exception);
    }

}

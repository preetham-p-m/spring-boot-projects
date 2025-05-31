package com.pmp.kafka_core.payments.error;

public class NotRetryableException extends RuntimeException {

    public NotRetryableException(Exception exception) {
        super(exception);
    }

    public NotRetryableException(String message) {
        super(message);
    }
}

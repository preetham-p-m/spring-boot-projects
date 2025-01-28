package com.pmp.kafka_core.payments.error;

public class RetryableException extends RuntimeException {

    public RetryableException(Throwable cause) {
        super(cause);
    }

    public RetryableException(String message) {
        super(message);
    }
}

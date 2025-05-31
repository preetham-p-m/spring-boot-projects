package com.pmp.kafka_producer_service.error;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Error {

    private LocalDateTime timeStamp;

    private String message;

    private String details;
}

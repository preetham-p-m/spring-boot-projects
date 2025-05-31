package com.pmp.kafka_core.payments.events;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepositRequestedEvent implements Serializable {

    private String senderId;

    private String recepientId;

    private BigDecimal amount;

}
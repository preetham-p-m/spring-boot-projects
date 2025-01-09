package com.pmp.kafka_producer_service.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent implements Serializable {

    private String productId;

    private String title;

    private BigDecimal price;

    private Integer quantity;
}

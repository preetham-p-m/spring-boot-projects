package com.pmp.kafka_consumer_service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pmp.kafka_core.ProductCreatedEvent;

@Component
@KafkaListener(topics = "product-created-event-topic")
public class ProductCreatedEventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaHandler
    public void handler(ProductCreatedEvent productCreatedEvent) {
        this.logger.info(
                "Received a new event: " + productCreatedEvent.getTitle() + " - " + productCreatedEvent.getProductId());
    }

}

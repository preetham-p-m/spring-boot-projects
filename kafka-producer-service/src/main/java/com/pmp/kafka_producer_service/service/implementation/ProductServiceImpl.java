package com.pmp.kafka_producer_service.service.implementation;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.pmp.kafka_producer_service.error.MessageNotSendException;
import com.pmp.kafka_producer_service.model.CreateProductRestModel;
import com.pmp.kafka_producer_service.model.ProductCreatedEvent;
import com.pmp.kafka_producer_service.service.interfaces.ProductService;

@Service
class ProductServiceImpl implements ProductService {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductRestModel product) {

        String productId = UUID.randomUUID().toString();

        // Persist before publish event

        var productCreatedEvent = new ProductCreatedEvent(productId, product.getTitle(), product.getPrice(),
                product.getQuantity());
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate
                .send("product-created-event-topic", productId, productCreatedEvent);

        future.whenComplete((result, exception) -> {
            if (exception != null) {
                this.logger.error("**** Failed to send ProductCreatedEvent: " + exception.getMessage());
            } else {
                this.logger.info("**** ProductCreatedEvent message sent successfully: " + result.getRecordMetadata());
            }
        });

        this.logger.info("**** Product {} created successfully", productId);

        return productId;
    }

    @Override
    public String createProductSyncWithJoin(CreateProductRestModel product) {

        String productId = UUID.randomUUID().toString();

        // Persist before publish event

        var productCreatedEvent = new ProductCreatedEvent(productId, product.getTitle(), product.getPrice(),
                product.getQuantity());
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate
                .send("product-created-event-topic", productId, productCreatedEvent);

        future.whenComplete((result, exception) -> {
            if (exception != null) {
                this.logger.error("**** Failed to send ProductCreatedEvent: " + exception.getMessage());
            } else {
                this.logger.info("**** ProductCreatedEvent message sent successfully: " + result.getRecordMetadata());
            }
        });

        // Using join to block the main thread until the future is complete, ensuring
        // the task is executed synchronously.
        future.join();

        this.logger.info("**** Product {} created successfully", productId);

        return productId;
    }

    @Override
    public String createProductSync(CreateProductRestModel product) {

        String productId = UUID.randomUUID().toString();

        // Persist before publish event

        var productCreatedEvent = new ProductCreatedEvent(productId, product.getTitle(), product.getPrice(),
                product.getQuantity());

        try {
            SendResult<String, ProductCreatedEvent> result = kafkaTemplate
                    .send("product-created-event-topic", productId, productCreatedEvent).get();
            this.logger.debug(
                    "product-created-event-topic created successfully in topic {} to partition {} and offset {}",
                    result.getRecordMetadata().topic(), result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset());
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
            throw new MessageNotSendException(e);
        }

        this.logger.info("**** Product {} created successfully", productId);

        return productId;
    }
}

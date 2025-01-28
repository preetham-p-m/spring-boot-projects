package com.pmp.kafka_consumer_service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.pmp.kafka_consumer_service.error.NonRetryableException;
import com.pmp.kafka_consumer_service.error.RetryableException;
import com.pmp.kafka_consumer_service.model.ProcessedEventEntity;
import com.pmp.kafka_consumer_service.repository.interfaces.ProcessedEventRepository;
import com.pmp.kafka_core.ProductCreatedEvent;

@Component
@KafkaListener(topics = "product-created-event-topic")
public class ProductCreatedEventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RestTemplate restTemplate;
    private ProcessedEventRepository processedEventRepository;

    public ProductCreatedEventHandler(RestTemplate restTemplate, ProcessedEventRepository processedEventRepository) {
        super();
        this.restTemplate = restTemplate;
        this.processedEventRepository = processedEventRepository;
    }

    @KafkaHandler
    @Transactional
    public void handler(@Payload ProductCreatedEvent productCreatedEvent,
            @Header(value = "messageId", required = true) String messageId, // By default required is true
            @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {
        this.logger.info(
                "Received a new event: " + productCreatedEvent.getTitle() + " - " + productCreatedEvent.getProductId());

        // Check if the event has already been processed
        if (this.processedEventRepository.findByMessageId(messageId) != null) {
            this.logger.info("Event already processed: " + messageId + " - " + productCreatedEvent.getProductId());
            return;
        }

        try {
            String requestUrl = "http://localhost:8082/response/200"; // use 200 or 500

            ResponseEntity<String> response = this.restTemplate.exchange(requestUrl, HttpMethod.GET, null,
                    String.class);

            if (response.getStatusCode().value() == HttpStatus.OK.value()) {
                this.logger.info("Received response from remote server: " + response.getBody());
            }
        } catch (ResourceAccessException ex) {
            this.logger.error(ex.getMessage(), ex);
            throw new RetryableException(ex);
        } catch (HttpServerErrorException ex) {
            this.logger.error(ex.getMessage(), ex);
            throw new NonRetryableException(ex);
        } catch (Exception ex) {
            this.logger.error(ex.getMessage(), ex);
            throw new NonRetryableException(ex);
        }

        // Save the processed event
        this.processedEventRepository.save(new ProcessedEventEntity(messageId, productCreatedEvent.getProductId()));
    }

}

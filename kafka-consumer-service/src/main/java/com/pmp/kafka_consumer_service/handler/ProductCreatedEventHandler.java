package com.pmp.kafka_consumer_service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.pmp.kafka_consumer_service.error.NonRetryableException;
import com.pmp.kafka_consumer_service.error.RetryableException;
import com.pmp.kafka_core.ProductCreatedEvent;

@Component
@KafkaListener(topics = "product-created-event-topic")
public class ProductCreatedEventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RestTemplate restTemplate;

    public ProductCreatedEventHandler(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

    @KafkaHandler
    public void handler(ProductCreatedEvent productCreatedEvent) {
        this.logger.info(
                "Received a new event: " + productCreatedEvent.getTitle() + " - " + productCreatedEvent.getProductId());

        try {
            String requestUrl = "http://localhost:8082/response/500"; // use 200 or 500

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

    }

}

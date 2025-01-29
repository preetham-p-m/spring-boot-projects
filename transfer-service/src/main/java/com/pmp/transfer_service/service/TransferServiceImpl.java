package com.pmp.transfer_service.service;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.pmp.kafka_core.payments.events.DepositRequestedEvent;
import com.pmp.kafka_core.payments.events.WithdrawalRequestedEvent;
import com.pmp.transfer_service.error.TransferServiceException;
import com.pmp.transfer_service.model.TransferEntity;

@Service
public class TransferServiceImpl implements TransferService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private KafkaTemplate<String, Object> kafkaTemplate;
	private Environment environment;
	private RestTemplate restTemplate;

	public TransferServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, Environment environment,
			RestTemplate restTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.environment = environment;
		this.restTemplate = restTemplate;
	}

	@Transactional(value = "kafkaTransactionManager", rollbackFor = { TransferServiceException.class,
			ConnectException.class }, noRollbackFor = {})
	@Override
	public boolean transfer(TransferEntity transferRestModel) {
		WithdrawalRequestedEvent withdrawalEvent = new WithdrawalRequestedEvent(transferRestModel.getSenderId(),
				transferRestModel.getRecepientId(), transferRestModel.getAmount());
		DepositRequestedEvent depositEvent = new DepositRequestedEvent(transferRestModel.getSenderId(),
				transferRestModel.getRecepientId(), transferRestModel.getAmount());

		try {
			kafkaTemplate.send(environment.getProperty("withdraw-money-topic", "withdraw-money-topic"),
					withdrawalEvent);
			LOGGER.info("Sent event to withdrawal topic.");

			// Business logic that causes and error
			callRemoteService();

			kafkaTemplate.send(environment.getProperty("deposit-money-topic", "deposit-money-topic"), depositEvent);
			LOGGER.info("Sent event to deposit topic");

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new TransferServiceException(ex);
		}

		return true;
	}

	private ResponseEntity<String> callRemoteService() throws Exception {
		String requestUrl = "http://localhost:8082/response/503";
		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);

		if (response.getStatusCode().value() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
			throw new Exception("Destination Microservice not available");
		}

		if (response.getStatusCode().value() == HttpStatus.OK.value()) {
			LOGGER.info("Received response from mock service: " + response.getBody());
		}
		return response;
	}

}

package com.pmp.kafka_consumer_service.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmp.kafka_consumer_service.model.ProcessedEventEntity;

@Repository
public interface ProcessedEventRepository extends JpaRepository<ProcessedEventEntity, Long> {
    
    ProcessedEventEntity findByMessageId(String messageId);
}

package com.pmp.transfer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmp.transfer_service.model.TransferEntity;

public interface TransferRepository extends JpaRepository<TransferEntity, String> {

}

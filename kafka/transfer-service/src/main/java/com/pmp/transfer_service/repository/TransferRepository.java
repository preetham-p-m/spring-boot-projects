package com.pmp.transfer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmp.transfer_service.model.TransferEntity;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, String> {

}

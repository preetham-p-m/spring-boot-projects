package com.pmp.transfer_service.service;

import com.pmp.transfer_service.model.TransferRestModel;

public interface TransferService {
    public boolean transfer(TransferRestModel productPaymentRestModel);
}

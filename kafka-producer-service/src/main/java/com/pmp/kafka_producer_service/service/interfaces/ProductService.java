package com.pmp.kafka_producer_service.service.interfaces;

import com.pmp.kafka_producer_service.model.CreateProductRestModel;

public interface ProductService {

    String createProduct(CreateProductRestModel product);

    String createProductSyncWithJoin(CreateProductRestModel product);

    String createProductSync(CreateProductRestModel product);

}

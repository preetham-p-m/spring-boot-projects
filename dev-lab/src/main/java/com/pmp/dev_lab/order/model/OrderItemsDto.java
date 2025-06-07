package com.pmp.dev_lab.order.model;

import lombok.Data;

@Data
public class OrderItemsDto {
    private String itemCode;
    private int quantity;
}

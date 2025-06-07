package com.pmp.dev_lab.order.model;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String customerName;
    private List<OrderItemsDto> items;
}

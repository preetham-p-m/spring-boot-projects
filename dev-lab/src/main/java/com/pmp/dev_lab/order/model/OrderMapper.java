package com.pmp.dev_lab.order.model;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setCustomerName(orderRequestDto.getCustomerName());
        order.setItems(orderRequestDto.getItems().stream().map(this::toEntity).toList());
        return order;
    }

    public OrderItem toEntity(OrderItemsDto orderItemDto) {
        var orderItem = new OrderItem();
        orderItem.setItemCode(orderItemDto.getItemCode());
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }

}

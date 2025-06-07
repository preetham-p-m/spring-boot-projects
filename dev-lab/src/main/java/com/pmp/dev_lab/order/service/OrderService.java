package com.pmp.dev_lab.order.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.pmp.dev_lab.order.model.Order;
import com.pmp.dev_lab.order.model.OrderMapper;
import com.pmp.dev_lab.order.model.OrderRequestDto;
import com.pmp.dev_lab.order.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Order create(OrderRequestDto orderRequestDto) {
        var order = orderMapper.toEntity(orderRequestDto);
        return orderRepository.save(order);
    }

    public Order findByOrderId(Long id) {
        var orders = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + id));
        orders.getItems().size();
        return orders;
    }

    public Order findByOrderIdWithQuery(Long id) {
        return orderRepository.findOrderDetailsById(id).orElseThrow();
    }
}

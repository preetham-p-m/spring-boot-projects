package com.pmp.dev_lab.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmp.dev_lab.order.model.Order;
import com.pmp.dev_lab.order.model.OrderRequestDto;
import com.pmp.dev_lab.order.service.OrderService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/Orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequest) {
        var order = orderService.create(orderRequest);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        var order = orderService.findByOrderId(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("{id}/query")
    public ResponseEntity<Order> getOrderByIdQuery(@PathVariable Long id) {
        var order = orderService.findByOrderIdWithQuery(id);
        return ResponseEntity.ok(order);
    }
}

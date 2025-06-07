package com.pmp.dev_lab.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.pmp.dev_lab.order.model.Order;
import com.pmp.dev_lab.order.model.OrderRequestDto;
import com.pmp.dev_lab.order.service.OrderService;

class OrderControllerTest {

    private OrderService orderService;
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        this.orderService = mock(OrderService.class);
        this.orderController = new OrderController(orderService);
    }

    @Test
    void createOrder_ShouldReturnCreatedOrder_WithStatus201() {
        var orderRequest = new OrderRequestDto();
        var createdOrder = new Order();
        when(orderService.create(orderRequest)).thenReturn(createdOrder);

        var result = orderController.createOrder(orderRequest);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(createdOrder, result.getBody());
        verify(orderService).create(orderRequest);
    }

    @Test
    void getOrderById_ShouldReturnOrder_WithStatus200() {
        var orderId = 1L;
        var order = new Order();
        when(orderService.findByOrderId(orderId)).thenReturn(order);

        var result = orderController.getOrderById(orderId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(order, result.getBody());
        verify(orderService).findByOrderId(orderId);
    }

    @Test
    void getOrderByIdQuery_ShouldReturnOrder_WithStatus200() {
        var order = new Order();
        var orderId = 1L;
        when(orderService.findByOrderIdWithQuery(orderId)).thenReturn(order);

        var result = orderController.getOrderByIdQuery(orderId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(order, result.getBody());
        verify(orderService, atLeastOnce()).findByOrderIdWithQuery(orderId);
    }

}

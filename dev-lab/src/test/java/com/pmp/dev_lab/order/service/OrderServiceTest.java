package com.pmp.dev_lab.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pmp.dev_lab.order.model.Order;
import com.pmp.dev_lab.order.model.OrderMapper;
import com.pmp.dev_lab.order.model.OrderRequestDto;
import com.pmp.dev_lab.order.repository.OrderRepository;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new Order();
        order.setId(1L);
        order.setItems(Collections.emptyList());

        orderRequestDto = new OrderRequestDto();
    }

    @Test
    void create_shouldMapAndSaveOrder() {
        when(orderMapper.toEntity(orderRequestDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.create(orderRequestDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(orderMapper).toEntity(orderRequestDto);
        verify(orderRepository).save(order);
    }

    @Test
    void findByOrderId_shouldReturnOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        var result = orderService.findByOrderId(1L);

        assertNotNull(result);
        assertEquals(result.getId(), order.getId());
    }

    @Test
    void findByOrderId_shouldThrowIfNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> orderService.findByOrderId(1L));

        assertEquals("Order not found with id: 1", ex.getMessage());
    }
}

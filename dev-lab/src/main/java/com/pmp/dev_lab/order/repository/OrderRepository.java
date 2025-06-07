package com.pmp.dev_lab.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pmp.dev_lab.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o JOIN FETCH o.items where o.id = :id")
    public Optional<Order> findOrderDetailsById(@Param("id") Long id);
}

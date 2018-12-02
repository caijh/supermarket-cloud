package com.coding.supermarket.domain.order.repository;

import com.coding.supermarket.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

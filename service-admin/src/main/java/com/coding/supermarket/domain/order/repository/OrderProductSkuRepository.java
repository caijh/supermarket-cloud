package com.coding.supermarket.domain.order.repository;

import com.coding.supermarket.domain.model.OrderProductSku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductSkuRepository extends JpaRepository<OrderProductSku, Long> {
}

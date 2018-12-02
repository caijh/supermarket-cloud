package com.coding.supermarket.domain.order.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.supermarket.domain.model.Order;
import com.coding.supermarket.domain.order.repository.OrderCacheRepository;
import org.springframework.data.jpa.repository.JpaRepository;


@Named
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderCacheRepository orderCacheRepository;

    @Override
    public JpaRepository<Order, Long> getRepository() {
        return orderCacheRepository;
    }
}

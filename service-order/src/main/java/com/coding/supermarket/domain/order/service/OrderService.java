package com.coding.supermarket.domain.order.service;

import java.util.List;

import com.coding.serviceorder.request.OrderPreReqBody;
import com.coding.supermarket.domain.order.model.Order;
import com.coding.supermarket.domain.product.model.ProductSku;

public interface OrderService {

    List<ProductSku> preOrder(OrderPreReqBody reqBody);

    Order detail(Long id);

    List<Order> list(Order order);

}

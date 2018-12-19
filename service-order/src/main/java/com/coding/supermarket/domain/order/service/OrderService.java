package com.coding.supermarket.domain.order.service;

import java.util.List;

import com.coding.commons.base.BizException;
import com.coding.serviceorder.request.OrderAddReqBody;
import com.coding.serviceorder.request.OrderPreReqBody;
import com.coding.supermarket.domain.order.model.Order;
import com.coding.supermarket.domain.order.model.PreOrder;

public interface OrderService {

    PreOrder prepareOrder(OrderPreReqBody reqBody);

    Order detail(Long id);

    List<Order> list(Order order);

    void add(OrderAddReqBody preOrder) throws BizException;

}

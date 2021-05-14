package com.github.caijh.supermarket.order.service;

import java.util.List;

import com.coding.commons.base.BizException;
import com.coding.supermarket.domain.order.model.Order;
import com.github.PreOrder;
import com.github.caijh.serviceorder.request.OrderAddReqBody;
import com.github.caijh.serviceorder.request.OrderPreReqBody;

public interface OrderService {

    PreOrder prepareOrder(OrderPreReqBody reqBody);

    Order detail(Long id);

    List<Order> list(Order order);

    void add(OrderAddReqBody preOrder) throws BizException;

}

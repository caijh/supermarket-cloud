package com.coding.serviceorder.controller;

import java.util.List;

import com.coding.commons.base.BizException;
import com.coding.commons.domain.order.model.Order;
import com.coding.commons.domain.order.service.OrderService;
import com.coding.commons.util.BeanUtils;
import com.coding.serviceorder.request.OrderAddReqBody;
import com.coding.serviceorder.request.OrderListReqBody;
import com.coding.serviceorder.request.OrderPreReqBody;
import javax.inject.Inject;

import com.coding.supermarket.domain.order.model.Order;
import com.coding.supermarket.domain.order.service.OrderService;
import com.coding.supermarket.domain.product.model.ProductSku;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Inject
    private OrderService orderService;

    /**
     * 订单列表.
     */
    @PostMapping(value = "/list")
    public List<Order> list(@RequestBody OrderListReqBody reqBody) {
        Order order = new Order();
        BeanUtils.copyProperties(reqBody, order);
        List<Order> orders = orderService.list(order);

        return orders;
    }

    @GetMapping(value = "/detail")
    public Order detail(@RequestParam Long id) throws BizException {
        Order order = orderService.detail(id);
        return order;
    }

    /**
     * 确认订单.
     */
    @PostMapping(value = "/pre")
    public List<ProductSku> prepare(@RequestBody OrderPreReqBody reqBody) {
        return orderService.preOrder(reqBody);
    }

    /**
     * 提交订单.
     */
    @PostMapping(value = "/add")
    public void addOrder(@RequestBody OrderAddReqBody reqBody) {

    }

}

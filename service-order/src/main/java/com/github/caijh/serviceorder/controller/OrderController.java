package com.github.caijh.serviceorder.controller;

import java.util.List;
import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.domain.order.model.Order;
import com.github.PreOrder;
import com.github.caijh.serviceorder.request.OrderAddReqBody;
import com.github.caijh.serviceorder.request.OrderListReqBody;
import com.github.caijh.serviceorder.request.OrderPreReqBody;
import com.github.caijh.supermarket.order.service.OrderService;
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
        List<Order> orders = this.orderService.list(order);

        return orders;
    }

    @GetMapping(value = "/detail")
    public Order detail(@RequestParam Long id) throws BizException {
        Order order = this.orderService.detail(id);
        return order;
    }

    /**
     * 确认订单.
     */
    @PostMapping(value = "/pre")
    public PreOrder prepare(@RequestBody OrderPreReqBody reqBody) {
        return this.orderService.prepareOrder(reqBody);
    }

    /**
     * 提交订单.
     */
    @PostMapping(value = "/add")
    public void addOrder(@RequestBody OrderAddReqBody reqBody) throws BizException {

        this.orderService.add(reqBody);
    }

}

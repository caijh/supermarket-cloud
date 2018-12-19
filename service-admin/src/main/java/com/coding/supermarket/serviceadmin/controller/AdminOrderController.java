package com.coding.supermarket.serviceadmin.controller;

import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.domain.order.model.Order;
import com.coding.supermarket.domain.order.service.OrderService;
import com.coding.supermarket.serviceadmin.request.order.OrderListReqBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/order")
public class AdminOrderController {

    @Inject
    private OrderService orderService;

    /**
     * 订单列表.
     */
    @PostMapping(value = "/list")
    public Page<Order> list(@RequestBody OrderListReqBody reqBody) {
        Order order = new Order();
        BeanUtils.copyProperties(reqBody, order);
        return orderService.list(order, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));
    }

    @GetMapping(value = "/detail")
    public Order detail(@RequestParam(value = "id") Long id) throws BizException {
        return orderService.detail(id);
    }
}

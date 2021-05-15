package com.github.caijh.supermarket.admin.controller;

import javax.inject.Inject;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.framework.web.controller.BaseController;
import com.github.caijh.supermarket.admin.request.order.OrderListReqBody;
import com.github.caijh.supermarket.order.model.Order;
import com.github.caijh.supermarket.order.service.OrderService;
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
public class OrderController extends BaseController {

    @Inject
    private OrderService orderService;

    /**
     * 订单列表.
     */
    @PostMapping(value = "/list")
    public Page<Order> list(@RequestBody OrderListReqBody reqBody) {
        Order order = new Order();
        BeanUtils.copyProperties(reqBody, order);
        return this.orderService.list(order, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));
    }

    @GetMapping(value = "/detail")
    public Order detail(@RequestParam(value = "id") Long id) {
        return this.orderService.detail(id);
    }

}

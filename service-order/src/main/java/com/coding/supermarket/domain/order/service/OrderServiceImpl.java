package com.coding.supermarket.domain.order.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.serviceorder.ProductClient;
import com.coding.serviceorder.request.OrderPreReqBody;
import com.coding.supermarket.domain.order.mapper.OrderMapper;
import com.coding.supermarket.domain.order.model.Order;
import com.coding.supermarket.domain.product.model.ProductSku;

@Named
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderMapper orderMapper;

    @Inject
    private ProductClient productClient;

    @Override
    public List<ProductSku> preOrder(OrderPreReqBody reqBody) {
        List<OrderPreReqBody.ProductToBuy> products = reqBody.getProducts();
        List<Long> productSkuIds = products.stream().map(OrderPreReqBody.ProductToBuy::getProductSkuId).collect(Collectors.toList());
        List<ProductSku> productSkuList = productClient.findByProductSkuIds(productSkuIds);


        return productSkuList;
    }

    @Override
    public Order detail(Long id) {
        return orderMapper.get(id);
    }

    @Override
    public List<Order> list(Order order) {
        return null;
    }

}

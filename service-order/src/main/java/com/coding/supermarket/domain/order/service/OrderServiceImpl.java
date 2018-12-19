package com.coding.supermarket.domain.order.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.util.DateUtils;
import com.coding.serviceorder.ProductClient;
import com.coding.serviceorder.request.OrderAddReqBody;
import com.coding.serviceorder.request.OrderPreReqBody;
import com.coding.serviceorder.request.ProductToBuy;
import com.coding.supermarket.domain.order.mapper.OrderMapper;
import com.coding.supermarket.domain.order.model.Order;
import com.coding.supermarket.domain.order.model.OrderProductSku;
import com.coding.supermarket.domain.order.model.OrderStatus;
import com.coding.supermarket.domain.order.model.PreOrder;
import com.coding.supermarket.domain.product.model.ProductSku;
import com.coding.supermarket.domain.product.model.ProductStatus;

@Named
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderMapper orderMapper;

    @Inject
    private ProductClient productClient;

    @Inject
    private RedisUtils redisUtils;

    @Transactional
    @Override
    public PreOrder prepareOrder(OrderPreReqBody reqBody) {
        List<ProductToBuy> products = reqBody.getProducts();
        List<Long> productSkuIds = products.stream().map(ProductToBuy::getProductSkuId).collect(Collectors.toList());
        List<ProductSku> productSkuList = productClient.findByProductSkuIds(productSkuIds);
        PreOrder preOrder = new PreOrder();
        int sum = productSkuList.stream().mapToInt(ProductSku::getPrice).sum();
        preOrder.setProductSkuList(productSkuList);
        preOrder.setAmount(sum);
        preOrder.setPayAmount(sum);
        return preOrder;
    }

    @Override
    public Order detail(Long id) {
        return orderMapper.get(id);
    }

    @Override
    public List<Order> list(Order order) {
        return null;
    }

    @Override
    public void add(OrderAddReqBody reqBody) throws BizException {
        List<Long> productSkuIds = reqBody.getProducts().stream().map(ProductToBuy::getProductSkuId).collect(Collectors.toList());
        List<ProductSku> productSkuList = productClient.findByProductSkuIds(productSkuIds);
        int sum = productSkuList.stream().mapToInt(ProductSku::getPrice).sum();
        if (reqBody.getPayAmount() != sum) {
            throw new BizException("支付金额不一致");
        }
        if (validateProductSku(productSkuList)) {
            throw new BizException("");
        }
        Order order = new Order();
        Long orderId = getOrderId();
        order.setId(orderId);
        order.setUserId(reqBody.getUserId());
        order.setCreateTime(DateUtils.now());
        order.setStatus(OrderStatus.UN_PAY.getIndex());
        order.setPayAmount(reqBody.getPayAmount());
        order.setAmount(sum);
        order.setNo(getOrderNo(orderId));
        orderMapper.add(order);
        reqBody.getProductSkuList().forEach(e -> {
            OrderProductSku orderProductSku = new OrderProductSku();
            orderProductSku.set
        });
    }

    private boolean validateProductSku(List<ProductSku> productSkuList) {
        return productSkuList.stream().noneMatch(e -> e.getStatus() != null && ProductStatus.ON_SALE.getIndex() == e.getStatus());
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Long getOrderId() {
        return redisUtils.getRedisTemplate().opsForValue().increment("supermarket:order:id", 1);
    }

    private String getOrderNo(Long orderId) {
        return DateUtils.format(DateUtils.now(), "yyyyMMddHHmmss").concat(String.valueOf(orderId));
    }

    private int calculatePayAmount(PreOrder preOrder) {
        return preOrder.getProductSkuList().stream().mapToInt(ProductSku::getPrice).sum();
    }

}

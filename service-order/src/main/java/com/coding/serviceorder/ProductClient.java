package com.coding.serviceorder;

import java.util.List;

import com.coding.supermarket.domain.product.model.ProductSku;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-product")
public interface ProductClient {

    @RequestMapping(value = "/product/sku/list")
    List<ProductSku> findByProductSkuIds(List<Long> productSkuIds);
}

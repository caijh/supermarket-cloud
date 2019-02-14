package com.coding.supermarket.domain.product.service;

import java.util.List;

import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.supermarket.domain.product.model.Product;
import com.coding.supermarket.domain.product.vo.ProductSkuVo;
import com.coding.supermarket.domain.product.vo.ProductVo;

public interface ProductService extends JpaBaseService<Product, Long> {

    List<ProductSkuVo> findSkuList(List<Long> productSkuIds);

    ProductVo findById(Long id);

}

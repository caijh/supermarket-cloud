package com.coding.supermarket.domain.product.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.supermarket.domain.product.model.Product;

public interface ProductService extends JpaBaseService<Product, Long> {
    void add(Product product) throws BizException;

    void update(Product product) throws BizException;

    void delete(Long productId) throws BizException;
}

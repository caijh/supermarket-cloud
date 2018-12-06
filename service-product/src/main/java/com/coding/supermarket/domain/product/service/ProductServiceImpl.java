package com.coding.supermarket.domain.product.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.supermarket.domain.product.model.Product;
import com.coding.supermarket.domain.repository.ProductCacheRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductCacheRepository productCacheRepository;

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return productCacheRepository;
    }
}

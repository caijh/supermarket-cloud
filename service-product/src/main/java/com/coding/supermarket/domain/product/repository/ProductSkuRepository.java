package com.coding.supermarket.domain.product.repository;

import com.coding.supermarket.domain.product.model.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {

}

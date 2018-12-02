package com.coding.supermarket.domain.product.repository;

import com.coding.supermarket.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

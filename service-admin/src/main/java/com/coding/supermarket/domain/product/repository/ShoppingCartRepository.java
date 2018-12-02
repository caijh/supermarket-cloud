package com.coding.supermarket.domain.product.repository;

import com.coding.supermarket.domain.product.model.ShoppingCartSku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartSku, Long> {
}

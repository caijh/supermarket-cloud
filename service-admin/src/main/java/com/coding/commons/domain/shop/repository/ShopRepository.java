package com.coding.commons.domain.shop.repository;

import com.coding.commons.domain.shop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}

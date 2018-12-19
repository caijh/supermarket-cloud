package com.coding.supermarket.domain.product.repository;

import com.coding.supermarket.domain.product.model.CategoryProductAttr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductAttrRepository extends JpaRepository<CategoryProductAttr, Long> {
}

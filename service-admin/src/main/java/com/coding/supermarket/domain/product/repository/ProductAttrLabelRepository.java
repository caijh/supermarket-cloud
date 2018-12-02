package com.coding.supermarket.domain.product.repository;

import com.coding.supermarket.domain.product.model.ProductAttrLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttrLabelRepository extends JpaRepository<ProductAttrLabel, String> {
}

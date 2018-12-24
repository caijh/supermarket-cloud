package com.coding.supermarket.domain.product.repository;

import com.coding.supermarket.domain.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

package com.githuba.caijh.supermarket.product.repository;


import com.github.caijh.supermarket.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

}

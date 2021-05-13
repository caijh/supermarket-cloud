package com.githuba.caijh.supermarket.product.repository;


import com.github.caijh.supermarket.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

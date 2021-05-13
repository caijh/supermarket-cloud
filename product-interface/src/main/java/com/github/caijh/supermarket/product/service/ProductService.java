package com.github.caijh.supermarket.product.service;

import java.util.List;

import com.github.caijh.supermarket.base.BaseService;
import com.github.caijh.supermarket.product.model.Product;
import com.github.caijh.supermarket.product.vo.ProductSkuVo;
import com.github.caijh.supermarket.product.vo.ProductVo;


public interface ProductService extends BaseService<Product, Long> {

    List<ProductSkuVo> findSkuList(List<Long> productSkuIds);

    ProductVo findById(Long id);

}

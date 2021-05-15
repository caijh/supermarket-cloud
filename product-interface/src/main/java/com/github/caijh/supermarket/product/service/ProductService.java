package com.github.caijh.supermarket.product.service;

import java.util.List;
import javax.annotation.Nonnull;

import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.base.service.BaseService;
import com.github.caijh.supermarket.product.model.Product;
import com.github.caijh.supermarket.product.vo.ProductSkuVo;
import com.github.caijh.supermarket.product.vo.ProductVo;
import org.jetbrains.annotations.NotNull;


public interface ProductService extends BaseService<Product, Long> {

    @Override
    void add(@NotNull Product product) throws BizException;

    @Override
    void update(@Nonnull Product product) throws BizException;

    void delete(Long productId) throws BizException;

    List<ProductSkuVo> findSkuList(List<Long> productSkuIds);

    ProductVo findById(Long id);

}

package com.github.caijh.supermarket.product.service;

import javax.annotation.Nonnull;

import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.base.service.BaseService;
import com.github.caijh.supermarket.product.model.ProductCategory;
import org.jetbrains.annotations.NotNull;

public interface ProductCategoryService extends BaseService<ProductCategory, Long> {

    @Override
    void add(@NotNull ProductCategory category);

    @Override
    void update(@Nonnull ProductCategory category) throws BizException;

}

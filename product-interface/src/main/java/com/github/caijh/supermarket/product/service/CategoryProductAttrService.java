package com.github.caijh.supermarket.product.service;

import javax.annotation.Nonnull;

import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.base.service.BaseService;
import com.github.caijh.supermarket.product.model.CategoryProductAttr;
import org.jetbrains.annotations.NotNull;

public interface CategoryProductAttrService extends BaseService<CategoryProductAttr, Long> {

    @Override
    void add(@NotNull CategoryProductAttr productAttrLabel) throws BizException;

    @Override
    void update(@Nonnull CategoryProductAttr productAttrLabel) throws BizException;

}

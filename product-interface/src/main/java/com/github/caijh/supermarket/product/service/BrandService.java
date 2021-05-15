package com.github.caijh.supermarket.product.service;

import javax.annotation.Nonnull;

import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.base.service.BaseService;
import com.github.caijh.supermarket.product.model.Brand;
import org.jetbrains.annotations.NotNull;

public interface BrandService extends BaseService<Brand, String> {

    @Override
    void add(@NotNull Brand brand);

    @Override
    void update(@Nonnull Brand brand) throws BizException;

    void delete(String id) throws BizException;

}

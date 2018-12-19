package com.coding.supermarket.domain.product.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.supermarket.domain.product.model.CategoryProductAttr;

public interface CategoryProductAttrService extends JpaBaseService<CategoryProductAttr, Long> {
    void add(CategoryProductAttr productAttrLabel) throws BizException;

    void update(CategoryProductAttr productAttrLabel) throws BizException;
}

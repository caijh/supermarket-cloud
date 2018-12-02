package com.coding.supermarket.domain.product.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.supermarket.domain.product.model.Category;

public interface CategoryService extends JpaBaseService<Category, Long> {
    void add(Category category);

    void update(Category category) throws BizException;
}

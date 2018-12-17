package com.coding.supermarket.domain.product.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.supermarket.domain.product.model.Brand;

public interface BrandService extends JpaBaseService<Brand, String> {
    void add(Brand brand);

    void update(Brand brand) throws BizException;

    void delete(String id) throws BizException;
}

package com.coding.supermarket.domain.product.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.supermarket.domain.product.model.ProductAttrLabel;

public interface ProductAttrLabelService extends JpaBaseService<ProductAttrLabel, String> {
    void add(ProductAttrLabel productAttrLabel) throws BizException;

    void update(ProductAttrLabel productAttrLabel) throws BizException;
}

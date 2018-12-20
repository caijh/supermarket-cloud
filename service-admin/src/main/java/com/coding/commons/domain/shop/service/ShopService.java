package com.coding.commons.domain.shop.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.commons.domain.shop.model.Shop;

public interface ShopService extends JpaBaseService<Shop, Long> {

    void add(Shop shop);

    void update(Shop shop) throws BizException;

}

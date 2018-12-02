package com.coding.supermarket.domain.product.service;

import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.supermarket.domain.product.model.ShoppingCartSku;

public interface ShoppingCartService extends JpaBaseService<ShoppingCartSku, Long> {

    void add(ShoppingCartSku shoppingCartSku);

}

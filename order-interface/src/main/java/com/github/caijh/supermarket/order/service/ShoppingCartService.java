package com.github.caijh.supermarket.order.service;

import com.github.caijh.supermarket.base.service.BaseService;
import com.github.caijh.supermarket.order.model.ShoppingCartSku;
import org.jetbrains.annotations.NotNull;

public interface ShoppingCartService extends BaseService<ShoppingCartSku, Long> {

    @Override
    void add(@NotNull ShoppingCartSku shoppingCartSku);

}

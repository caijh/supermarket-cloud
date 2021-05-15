package com.github.caijh.supermarket.order.service.impl;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.caijh.commons.util.DateUtils;
import com.github.caijh.supermarket.order.model.ShoppingCartSku;
import com.github.caijh.supermarket.order.repository.ShoppingCartRepository;
import com.github.caijh.supermarket.order.service.ShoppingCartService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public void add(@NotNull ShoppingCartSku shoppingCartSku) {
        Example<ShoppingCartSku> example =
                Example.of(ShoppingCartSku
                        .builder().userId(shoppingCartSku.getUserId())
                        .productSkuId(shoppingCartSku.getProductSkuId()).build());
        Optional<ShoppingCartSku> shoppingCartOptional = this.getRepository().findOne(example);
        if (shoppingCartOptional.isPresent()) {
            ShoppingCartSku oldShoppingCartSku = shoppingCartOptional.get();
            oldShoppingCartSku.setNum(oldShoppingCartSku.getNum() + 1);
            oldShoppingCartSku.setUpdateTime(DateUtils.now());
            this.getRepository().save(oldShoppingCartSku);
        } else {
            this.getRepository().save(shoppingCartSku);
        }
    }

    @Override
    public JpaRepository<ShoppingCartSku, Long> getRepository() {
        return this.shoppingCartRepository;
    }

}

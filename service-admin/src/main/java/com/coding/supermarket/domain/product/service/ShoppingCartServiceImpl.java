package com.coding.supermarket.domain.product.service;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.util.DateUtils;
import com.coding.supermarket.domain.product.model.ShoppingCartSku;
import com.coding.supermarket.domain.product.repository.ProductRepository;
import com.coding.supermarket.domain.product.repository.ProductSkuCacheRepository;
import com.coding.supermarket.domain.product.repository.ShoppingCartCacheRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private ShoppingCartCacheRepository shoppingCartCacheRepository;
    @Inject
    private ProductRepository productRepository;
    @Inject
    private ProductSkuCacheRepository productSkuCacheRepository;

    @Override
    public void add(ShoppingCartSku shoppingCartSku) {
        Example<ShoppingCartSku> example =
            Example.of(ShoppingCartSku
                .builder().userId(shoppingCartSku.getUserId())
                .productSkuId(shoppingCartSku.getProductSkuId()).build());
        Optional<ShoppingCartSku> shoppingCartOptional = getRepository().findOne(example);
        if (shoppingCartOptional.isPresent()) {
            ShoppingCartSku oldShoppingCartSku = shoppingCartOptional.get();
            oldShoppingCartSku.setNum(oldShoppingCartSku.getNum() + 1);
            oldShoppingCartSku.setUpdateTime(DateUtils.now());
            getRepository().save(oldShoppingCartSku);
        } else {
            getRepository().save(shoppingCartSku);
        }
    }

    @Override
    public JpaRepository<ShoppingCartSku, Long> getRepository() {
        return shoppingCartCacheRepository;
    }
}

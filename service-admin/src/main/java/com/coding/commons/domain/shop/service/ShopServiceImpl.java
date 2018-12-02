package com.coding.commons.domain.shop.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.BizException;
import com.coding.commons.domain.shop.model.Shop;
import com.coding.commons.domain.shop.repository.ShopCacheRepository;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.DateUtils;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ShopServiceImpl implements ShopService {

    @Inject
    private ShopCacheRepository shopCacheRepository;

    @Override
    public JpaRepository<Shop, Long> getRepository() {
        return shopCacheRepository;
    }

    @Override
    public void add(Shop shop) {
        getRepository().save(shop);
    }

    @Override
    public void update(Shop shop) throws BizException {
        Shop dbShop = shopCacheRepository.findById(shop.getId()).orElseThrow(() -> new BizException("shop not found"));
        BeanUtils.copyIgnoreNullProperties(shop, dbShop);

        shop.setUpdateTime(DateUtils.now());
        shopCacheRepository.save(dbShop);
    }
}

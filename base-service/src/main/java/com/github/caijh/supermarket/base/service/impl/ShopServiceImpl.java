package com.github.caijh.supermarket.base.service.impl;

import javax.inject.Inject;

import com.github.caijh.supermarket.base.model.Shop;
import com.github.caijh.supermarket.base.repository.ShopRepository;
import com.github.caijh.supermarket.base.service.ShopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Inject
    private ShopRepository shopRepository;

    @Override
    public JpaRepository<Shop, Long> getRepository() {
        return this.shopRepository;
    }

}

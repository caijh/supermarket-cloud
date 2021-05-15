package com.github.caijh.supermarket.product.service.impl;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.github.caijh.commons.util.Base64Utils;
import com.github.caijh.commons.util.DateUtils;
import com.github.caijh.framework.core.enums.CommonStatus;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.product.model.Brand;
import com.github.caijh.supermarket.product.repository.BrandRepository;
import com.github.caijh.supermarket.product.service.BrandService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class BrandServiceImpl implements BrandService {

    @Inject
    private BrandRepository brandRepository;

    @Override
    public JpaRepository<Brand, String> getRepository() {
        return this.brandRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void add(@NotNull Brand brand) {
        brand.setId(Base64Utils.encrypt(brand.getName().getBytes()));
        brand.setStatus(CommonStatus.IN_USE.getIndex());
        brand.setCreateTime(DateUtils.now());
        this.getRepository().save(brand);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void update(@Nonnull Brand brand) throws BizException {
        Brand oldBrand = this.getRepository().findById(brand.getId())
                             .orElseThrow(() -> new BizException("brand not found"));
        BeanUtils.copyProperties(brand, oldBrand);
        this.getRepository().save(oldBrand);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void delete(String id) throws BizException {
        Brand brand = this.getRepository().findById(id).orElseThrow(() -> new BizException("brand not found"));
        brand.setStatus(CommonStatus.DELETED.getIndex());
        this.getRepository().save(brand);
    }

}

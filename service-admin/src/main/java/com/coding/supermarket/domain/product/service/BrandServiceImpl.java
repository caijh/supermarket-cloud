package com.coding.supermarket.domain.product.service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.CommonStatus;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.DateUtils;
import com.coding.supermarket.domain.product.model.Brand;
import com.coding.supermarket.domain.product.repository.BrandCacheRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class BrandServiceImpl implements BrandService {

    @Inject
    private BrandCacheRepository brandCacheRepository;

    @Override
    public JpaRepository<Brand, Long> getRepository() {
        return brandCacheRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void add(Brand brand) {
        brand.setStatus(CommonStatus.IN_USE.getIndex());
        brand.setCreateTime(DateUtils.now());
        getRepository().save(brand);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void update(Brand brand) throws BizException {
        Brand oldBrand = getRepository().findById(brand.getId()).orElseThrow(() -> new BizException("brand not found"));
        BeanUtils.copyIgnoreNullProperties(brand, oldBrand);
        getRepository().save(oldBrand);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void delete(Long id) throws BizException {
        Brand brand = getRepository().findById(id).orElseThrow(() -> new BizException("brand not found"));
        brand.setStatus(CommonStatus.DELETED.getIndex());
        getRepository().save(brand);
    }
}

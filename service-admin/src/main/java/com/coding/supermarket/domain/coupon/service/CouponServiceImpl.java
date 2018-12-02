package com.coding.supermarket.domain.coupon.service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.CommonStatus;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.DateUtils;
import com.coding.supermarket.domain.coupon.model.Coupon;
import com.coding.supermarket.domain.coupon.model.CouponEntry;
import com.coding.supermarket.domain.coupon.repository.CouponCacheRepository;
import com.coding.supermarket.domain.coupon.repository.CouponEntryCacheRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class CouponServiceImpl implements CouponService {

    @Inject
    private CouponCacheRepository couponCacheRepository;

    @Inject
    private CouponEntryCacheRepository couponEntryCacheRepository;

    @Override
    public JpaRepository<Coupon, Long> getRepository() {
        return couponCacheRepository;
    }

    @Transactional
    @Override
    public void add(Coupon coupon) {
        coupon.setCreateTime(DateUtils.now());
        getRepository().save(coupon);
    }

    @Transactional
    @Override
    public void update(Coupon coupon) throws BizException {
        Coupon oldCoupon = getRepository().findById(coupon.getId()).orElseThrow(() -> new BizException("coupon not found"));
        BeanUtils.copyIgnoreNullProperties(coupon, oldCoupon);
        getRepository().save(oldCoupon);
    }

    @Transactional
    @Override
    public void delete(Long id) throws BizException {
        Coupon oldCoupon = getRepository().findById(id).orElseThrow(() -> new BizException("coupons not found"));
        oldCoupon.setStatus(CommonStatus.DELETED.getIndex());
        getRepository().save(oldCoupon);
    }

    @Override
    public Page<CouponEntry> listEntry(CouponEntry couponEntry, Pageable pageable) {
        return couponEntryCacheRepository.findAll(Example.of(couponEntry), pageable);
    }
}

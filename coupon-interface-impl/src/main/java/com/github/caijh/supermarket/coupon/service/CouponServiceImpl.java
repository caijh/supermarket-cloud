package com.github.caijh.supermarket.coupon.service;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.commons.util.DateUtils;
import com.github.caijh.framework.core.enums.CommonStatus;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.coupon.model.Coupon;
import com.github.caijh.supermarket.coupon.model.CouponEntry;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class CouponServiceImpl implements CouponService {

    @Inject
    private com.coding.supermarket.coupon.repository.CouponRepository couponCacheRepository;

    @Inject
    private com.coding.supermarket.coupon.repository.CouponEntryRepository couponEntryCacheRepository;

    @Override
    public JpaRepository<Coupon, Long> getRepository() {
        return this.couponCacheRepository;
    }

    @Transactional
    @Override
    public void add(@NotNull Coupon coupon) {
        coupon.setCreateTime(DateUtils.now());
        this.getRepository().save(coupon);
    }

    @Transactional
    @Override
    public void update(@Nonnull Coupon coupon) throws BizException {
        Coupon oldCoupon = this.getRepository().findById(coupon.getId())
                               .orElseThrow(() -> new BizException("coupon not found"));
        BeanUtils.copyIgnoreNullProperties(coupon, oldCoupon);
        this.getRepository().save(oldCoupon);
    }

    @Transactional
    @Override
    public void delete(Long id) throws BizException {
        Coupon oldCoupon = this.getRepository().findById(id).orElseThrow(() -> new BizException("coupons not found"));
        oldCoupon.setStatus(CommonStatus.DELETED.getIndex());
        this.getRepository().save(oldCoupon);
    }

    @Override
    public Page<CouponEntry> listEntry(CouponEntry couponEntry, Pageable pageable) {
        return this.couponEntryCacheRepository.findAll(Example.of(couponEntry), pageable);
    }

}

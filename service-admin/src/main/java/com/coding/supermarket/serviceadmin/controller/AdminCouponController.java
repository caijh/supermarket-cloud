package com.coding.supermarket.serviceadmin.controller;

import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.base.constant.Operate;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.domain.coupon.model.Coupon;
import com.coding.supermarket.domain.coupon.model.CouponEntry;
import com.coding.supermarket.domain.coupon.service.CouponService;
import com.coding.supermarket.serviceadmin.request.coupons.CouponAddReqBody;
import com.coding.supermarket.serviceadmin.request.coupons.CouponEntryListReqBody;
import com.coding.supermarket.serviceadmin.request.coupons.CouponListReqBody;
import com.coding.supermarket.serviceadmin.request.coupons.CouponUpdateReqBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/coupon")
public class AdminCouponController {

    @Inject
    private CouponService couponService;
    @Inject
    private LocaleMessageService localeMessageService;

    /**
     * 优惠券列表.
     */
    @PostMapping(value = "/list")
    public ResponseEntity<Page<Coupon>> list(@RequestBody CouponListReqBody reqBody) {
        Coupon coupon = new Coupon();
        coupon.setAmount((int) (reqBody.getAmount() * 100 / 100));
        Page<Coupon> page = couponService.list(coupon, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));
        return ResponseEntity.ok(page);
    }

    /**
     * 优惠券详情.
     */
    @GetMapping(value = "/detail")
    public ResponseEntity<Coupon> detail(@RequestParam Long id) throws BizException {
        Coupon coupon = couponService.detail(id);
        return ResponseEntity.ok(coupon);
    }

    /**
     * 新增优惠券.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody CouponAddReqBody reqBody) {
        Coupon coupon = new Coupon();
        BeanUtils.copyIgnoreNullProperties(reqBody, coupon);
        couponService.add(coupon);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.ADD_SUCCESS));
    }

    /**
     * 更新优惠券信息.
     */
    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody CouponUpdateReqBody reqBody) throws BizException {
        Coupon coupon = new Coupon();
        BeanUtils.copyIgnoreNullProperties(reqBody, coupon);
        couponService.update(coupon);

        return ResponseEntity.ok(localeMessageService.getMessage(Operate.UPDATE_SUCCESS));
    }

    /**
     * 删除优惠券.
     */
    @GetMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) throws BizException {
        couponService.delete(id);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.DEL_SUCCESS));
    }

    /**
     * 优惠券领取列表.
     */
    @PostMapping(value = "/entry/list")
    public ResponseEntity<Page<CouponEntry>> entryList(@RequestBody CouponEntryListReqBody reqBody) {
        CouponEntry couponEntry = new CouponEntry();
        Page<CouponEntry> page = couponService.listEntry(couponEntry, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));

        return ResponseEntity.ok(page);
    }
}

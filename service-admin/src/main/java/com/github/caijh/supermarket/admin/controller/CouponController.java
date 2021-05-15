package com.github.caijh.supermarket.admin.controller;

import javax.inject.Inject;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.framework.web.controller.BaseController;
import com.github.caijh.supermarket.admin.request.coupons.CouponAddReqBody;
import com.github.caijh.supermarket.admin.request.coupons.CouponEntryListReqBody;
import com.github.caijh.supermarket.admin.request.coupons.CouponListReqBody;
import com.github.caijh.supermarket.admin.request.coupons.CouponUpdateReqBody;
import com.github.caijh.supermarket.coupon.model.Coupon;
import com.github.caijh.supermarket.coupon.model.CouponEntry;
import com.github.caijh.supermarket.coupon.service.CouponService;
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
@RequestMapping(value = "/coupon")
public class CouponController extends BaseController {

    @Inject
    private CouponService couponService;

    /**
     * 优惠券列表.
     */
    @PostMapping(value = "/list")
    public Page<Coupon> list(@RequestBody CouponListReqBody reqBody) {
        Coupon coupon = new Coupon();
        coupon.setAmount((int) (reqBody.getAmount() * 100 / 100));
        return this.couponService.list(coupon, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));
    }

    /**
     * 优惠券详情.
     */
    @GetMapping(value = "/detail")
    public ResponseEntity<Coupon> detail(@RequestParam Long id) {
        Coupon coupon = this.couponService.detail(id);
        return ResponseEntity.ok(coupon);
    }

    /**
     * 新增优惠券.
     */
    @PostMapping(value = "/add")
    public void add(@RequestBody CouponAddReqBody reqBody) {
        Coupon coupon = new Coupon();
        BeanUtils.copyIgnoreNullProperties(reqBody, coupon);
        this.couponService.add(coupon);
    }

    /**
     * 更新优惠券信息.
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody CouponUpdateReqBody reqBody) {
        Coupon coupon = new Coupon();
        BeanUtils.copyIgnoreNullProperties(reqBody, coupon);
        this.couponService.update(coupon);
    }

    /**
     * 删除优惠券.
     */
    @GetMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        this.couponService.delete(id);
    }

    /**
     * 优惠券领取列表.
     */
    @PostMapping(value = "/entry/list")
    public ResponseEntity<Page<CouponEntry>> entryList(@RequestBody CouponEntryListReqBody reqBody) {
        CouponEntry couponEntry = new CouponEntry();
        Page<CouponEntry> page = this.couponService
            .listEntry(couponEntry, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));

        return ResponseEntity.ok(page);
    }

}

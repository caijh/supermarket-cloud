package com.github.caijh.supermarket.admin.controller;

import javax.inject.Inject;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.commons.util.DateUtils;
import com.github.caijh.framework.web.controller.BaseController;
import com.github.caijh.supermarket.admin.request.shop.ShopAddReqBody;
import com.github.caijh.supermarket.admin.request.shop.ShopListReqBody;
import com.github.caijh.supermarket.admin.request.shop.ShopUpdateReqBody;
import com.github.caijh.supermarket.base.model.Shop;
import com.github.caijh.supermarket.base.service.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.caijh.framework.core.enums.CommonStatus.IN_USE;

@RestController
@RequestMapping(value = "/shop")
public class ShopController extends BaseController {

    @Inject
    private ShopService shopService;


    /**
     * 门店列表.
     */
    @PostMapping(value = "/list")
    public Page<Shop> list(@RequestBody ShopListReqBody reqBody) {
        Shop shop = new Shop();
        shop.setName(reqBody.getName());
        shop.setStatus(IN_USE.getIndex());
        return this.shopService.list(shop, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));
    }

    /**
     * 新增门店.
     */
    @PostMapping(value = "/add")
    public void add(@RequestBody ShopAddReqBody reqBody) {
        Shop shop = new Shop();
        BeanUtils.copyIgnoreNullProperties(reqBody, shop);
        shop.setCreateTime(DateUtils.now());
        this.shopService.add(shop);
    }

    /**
     * 门店详情.
     */
    @GetMapping(value = "/detail")
    public ResponseEntity<Shop> detail(@RequestParam Long shopId) {
        Shop shop = this.shopService.detail(shopId);

        return ResponseEntity.ok(shop);
    }

    /**
     * 修改门店信息.
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody ShopUpdateReqBody reqBody) {
        Shop shop = new Shop();
        BeanUtils.copyIgnoreNullProperties(reqBody, shop);

        this.shopService.update(shop);
    }

}

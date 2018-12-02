package com.coding.serviceadmin.controller;

import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.base.constant.Operate;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.domain.shop.model.Shop;
import com.coding.commons.domain.shop.service.ShopService;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.DateUtils;
import com.coding.serviceadmin.request.shop.ShopAddReqBody;
import com.coding.serviceadmin.request.shop.ShopListReqBody;
import com.coding.serviceadmin.request.shop.ShopUpdateReqBody;
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
@RequestMapping(value = "/admin/shop")
public class AdminShopController {
    @Inject
    private ShopService shopService;

    @Inject
    private LocaleMessageService localeMessageService;

    /**
     * 门店列表.
     */
    @PostMapping(value = "/list")
    public ResponseEntity<Page<Shop>> list(@RequestBody ShopListReqBody reqBody) {
        Shop shop = new Shop();
        shop.setName(reqBody.getName());
        Page<Shop> page = shopService.list(shop, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));

        return ResponseEntity.ok(page);
    }

    /**
     * 新增门店.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody ShopAddReqBody reqBody) {
        Shop shop = new Shop();
        BeanUtils.copyIgnoreNullProperties(reqBody, shop);
        shop.setCreateTime(DateUtils.now());
        shopService.add(shop);

        return ResponseEntity.ok(localeMessageService.getMessage(Operate.ADD_SUCCESS));
    }

    /**
     * 门店详情.
     */
    @GetMapping(value = "/detail")
    public ResponseEntity<Shop> detail(@RequestParam Long shopId) throws BizException {
        Shop shop = shopService.detail(shopId);

        return ResponseEntity.ok(shop);
    }

    /**
     * 修改门店信息.
     */
    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody ShopUpdateReqBody reqBody) throws BizException {
        Shop shop = new Shop();
        BeanUtils.copyIgnoreNullProperties(reqBody, shop);

        shopService.update(shop);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.UPDATE_SUCCESS));
    }
}

package com.coding.serviceorder.controller;

import java.util.List;
import javax.inject.Inject;

import com.coding.commons.domain.product.service.ShoppingCartService;
import com.coding.commons.domain.product.vo.ShoppingCartSkuVo;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.DateUtils;
import com.coding.serviceorder.request.ShoppingCartAddReqBody;
import com.coding.serviceorder.request.ShoppingCartListReqBody;
import com.coding.supermarket.domain.product.model.ShoppingCartSku;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order/shopping/cart")
public class ShoppingCartController {

    @Inject
    private ShoppingCartService shoppingCartService;

    /**
     * 商品加入购物车.
     */
    @RequestMapping(value = "/product/add")
    public ResponseEntity<String> addProduct(@RequestBody ShoppingCartAddReqBody reqBody) {
        ShoppingCartSku shoppingCartSku = new ShoppingCartSku();
        BeanUtils.copyProperties(reqBody, shoppingCartSku);
        shoppingCartSku.setCreateTime(DateUtils.now());
        shoppingCartService.add(shoppingCartSku);
        return ResponseEntity.ok("加入购物车成功");
    }

    /**
     * 购物车商品列表.
     */
    @PostMapping(value = "/product/list")
    public List<ShoppingCartSkuVo> list(@RequestBody ShoppingCartListReqBody reqBody) {
        ShoppingCartSku shoppingCartSku = new ShoppingCartSku();
        BeanUtils.copyProperties(reqBody, shoppingCartSku);
        return shoppingCartService
            .listShoppingCartSkuVo(shoppingCartSku, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));
    }


}

package com.coding.serviceproduct.controller;

import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.util.BeanUtils;
import com.coding.serviceproduct.request.product.ProductListReqBody;
import com.coding.supermarket.domain.product.model.Product;
import com.coding.supermarket.domain.product.model.ProductStatus;
import com.coding.supermarket.domain.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Inject
    private ProductService productService;

    /**
     * 商品列表.
     */
    @PostMapping(value = "/list")
    public Page<Product> list(@RequestBody ProductListReqBody reqBody) {
        Product product = new Product();
        BeanUtils.copyProperties(reqBody, product);
        product.setStatus(ProductStatus.ON_SALE.getIndex());
        return productService
            .list(product, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));
    }

    /**
     * 商品详情.
     */
    @GetMapping(value = "/detail")
    public ResponseEntity<Product> detail(Long id) throws BizException {
        Product product = productService.detail(id);
        return ResponseEntity.ok(product);
    }

}

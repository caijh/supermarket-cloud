package com.github.caijh.supermarket.product.controller;

import java.util.List;
import javax.inject.Inject;

import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.supermarket.product.request.ProductListReqBody;
import org.springframework.beans.BeanUtils;
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
        product.setStatus(ProductStatusEnum.ON_SALE.getIndex());
        return this.productService.list(product, PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize()));
    }

    @PostMapping(value = "/sku/list")
    public List<ProductSkuVo> findProductSku(@RequestBody ProductSkuListReqBody reqBody) {
        return this.productService.findSkuList(reqBody.getProductSkuIds());
    }

    /**
     * 商品详情.
     */
    @GetMapping(value = "/detail")
    public ResponseEntity<ProductVo> detail(Long id) throws BizException {
        ProductVo product = this.productService.findById(id);
        return ResponseEntity.ok(product);
    }

}

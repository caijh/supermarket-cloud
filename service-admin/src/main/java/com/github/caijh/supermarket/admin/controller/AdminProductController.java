package com.github.caijh.supermarket.admin.controller;

import javax.inject.Inject;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.framework.web.controller.BaseController;
import com.github.caijh.supermarket.admin.request.product.CategoryProductAttrAddReqBody;
import com.github.caijh.supermarket.admin.request.product.CategoryProductAttrUpdateReqBody;
import com.github.caijh.supermarket.admin.request.product.ProductAddReqBody;
import com.github.caijh.supermarket.admin.request.product.ProductCategoryAddReqBody;
import com.github.caijh.supermarket.admin.request.product.ProductCategoryListReqBody;
import com.github.caijh.supermarket.admin.request.product.ProductCategoryUpdateReqBody;
import com.github.caijh.supermarket.admin.request.product.ProductUpdateReqBody;
import com.github.caijh.supermarket.product.model.CategoryProductAttr;
import com.github.caijh.supermarket.product.model.Product;
import com.github.caijh.supermarket.product.model.ProductCategory;
import com.github.caijh.supermarket.product.service.CategoryProductAttrService;
import com.github.caijh.supermarket.product.service.ProductCategoryService;
import com.github.caijh.supermarket.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/product")
public class AdminProductController extends BaseController {

    @Inject
    private ProductCategoryService productCategoryService;

    @Inject
    private CategoryProductAttrService categoryProductAttrService;

    @Inject
    private ProductService productService;

    /**
     * 商品分类.
     */
    @PostMapping(value = "/category/list")
    public Page<ProductCategory> list(@RequestBody ProductCategoryListReqBody reqBody) {
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(reqBody, category);
        Pageable pageable = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        return this.productCategoryService.list(category, pageable);
    }

    /**
     * 商品分类详情.
     *
     * @return ProductCategory
     */
    @GetMapping(value = "/category/detail")
    public ProductCategory detail(@RequestParam Long id) {
        return this.productCategoryService.detail(id);
    }

    /**
     * 新增商品分类.
     */
    @PostMapping(value = "/category/add")
    public void addCategory(@RequestBody ProductCategoryAddReqBody reqBody) {
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(reqBody, category);
        this.productCategoryService.add(category);
    }

    /**
     * 修改商品分类.
     */
    @PostMapping(value = "/category/update")
    public void updateCategory(@RequestBody ProductCategoryUpdateReqBody reqBody) {
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(reqBody, category);
        this.productCategoryService.update(category);
    }

    /**
     * 新增属性.
     */
    @PostMapping(value = "/category/attr/add")
    public void addAttrLabel(@RequestBody CategoryProductAttrAddReqBody reqBody) {
        CategoryProductAttr categoryProductAttr = new CategoryProductAttr();
        BeanUtils.copyIgnoreNullProperties(reqBody, categoryProductAttr);
        this.categoryProductAttrService.add(categoryProductAttr);
    }

    /**
     * 更新属性.
     */
    @PostMapping(value = "/category/attr/update")
    public void updateAttrLabel(@RequestBody CategoryProductAttrUpdateReqBody reqBody) {
        CategoryProductAttr categoryProductAttr = new CategoryProductAttr();
        BeanUtils.copyIgnoreNullProperties(reqBody, categoryProductAttr);
        this.categoryProductAttrService.update(categoryProductAttr);
    }

    /**
     * 新增商品.
     */
    @PostMapping(value = "/add")
    public void add(@RequestBody ProductAddReqBody reqBody) {
        Product product = new Product();
        BeanUtils.copyProperties(reqBody, product);
        this.productService.add(product);
    }

    /**
     * 更新商品.
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody ProductUpdateReqBody reqBody) {
        Product product = new Product();
        BeanUtils.copyProperties(reqBody, product);
        this.productService.update(product);
    }

    /**
     * 删除商品.
     */
    @GetMapping(value = "/delete")
    public void delete(@RequestParam Long productId) {
        this.productService.delete(productId);
    }

}

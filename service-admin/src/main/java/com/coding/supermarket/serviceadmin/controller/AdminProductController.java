package com.coding.supermarket.serviceadmin.controller;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.BizException;
import com.coding.commons.base.constant.Operate;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.domain.product.model.Category;
import com.coding.supermarket.domain.product.model.CategoryProductAttr;
import com.coding.supermarket.domain.product.model.Product;
import com.coding.supermarket.domain.product.service.CategoryProductAttrService;
import com.coding.supermarket.domain.product.service.CategoryService;
import com.coding.supermarket.domain.product.service.ProductService;
import com.coding.supermarket.serviceadmin.request.product.CategoryProductAttrAddReqBody;
import com.coding.supermarket.serviceadmin.request.product.CategoryProductAttrUpdateReqBody;
import com.coding.supermarket.serviceadmin.request.product.ProductAddReqBody;
import com.coding.supermarket.serviceadmin.request.product.ProductCategoryAddReqBody;
import com.coding.supermarket.serviceadmin.request.product.ProductCategoryListReqBody;
import com.coding.supermarket.serviceadmin.request.product.ProductCategoryUpdateReqBody;
import com.coding.supermarket.serviceadmin.request.product.ProductUpdateReqBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/product")
public class AdminProductController {

    @Inject
    @Named("productCategoryService")
    private CategoryService categoryService;

    @Inject
    @Named("productAttrLabelService")
    private CategoryProductAttrService categoryProductAttrService;

    @Inject
    private ProductService productService;

    @Inject
    private LocaleMessageService localeMessageService;

    /**
     * 商品分类.
     */
    @PostMapping(value = "/category/list")
    public Page<Category> list(@RequestBody ProductCategoryListReqBody reqBody) {
        Category category = new Category();
        BeanUtils.copyProperties(reqBody, category);
        Pageable pageable = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        return categoryService.list(category, pageable);
    }

    /**
     * 商品分类详情.
     */
    @GetMapping(value = "/category/detail")
    public ResponseEntity<Category> detail(@RequestParam Long id) throws BizException {
        Category category = categoryService.detail(id);

        return ResponseEntity.ok(category);
    }

    /**
     * 新增商品分类.
     */
    @PostMapping(value = "/category/add")
    public ResponseEntity<String> addCategory(@RequestBody ProductCategoryAddReqBody reqBody) {
        Category category = new Category();
        BeanUtils.copyProperties(reqBody, category);
        categoryService.add(category);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.ADD_SUCCESS));
    }

    /**
     * 修改商品分类.
     */
    @PostMapping(value = "/category/update")
    public ResponseEntity<String> updateCategory(@RequestBody ProductCategoryUpdateReqBody reqBody) throws BizException {
        Category category = new Category();
        BeanUtils.copyProperties(reqBody, category);
        categoryService.update(category);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.UPDATE_SUCCESS));
    }

    /**
     * 新增属性.
     */
    @PostMapping(value = "/category/attr/add")
    public ResponseEntity<String> addAttrLabel(@RequestBody CategoryProductAttrAddReqBody reqBody) throws BizException {
        CategoryProductAttr categoryProductAttr = new CategoryProductAttr();
        BeanUtils.copyIgnoreNullProperties(reqBody, categoryProductAttr);
        categoryProductAttrService.add(categoryProductAttr);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.ADD_SUCCESS));
    }

    /**
     * 更新属性.
     */
    @PostMapping(value = "/category/attr/update")
    public ResponseEntity<String> updateAttrLabel(@RequestBody CategoryProductAttrUpdateReqBody reqBody) throws BizException {
        CategoryProductAttr categoryProductAttr = new CategoryProductAttr();
        BeanUtils.copyIgnoreNullProperties(reqBody, categoryProductAttr);
        categoryProductAttrService.update(categoryProductAttr);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.UPDATE_SUCCESS));
    }

    /**
     * 新增商品.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody ProductAddReqBody reqBody) throws BizException {
        Product product = new Product();
        BeanUtils.copyProperties(reqBody, product);
        productService.add(product);

        return ResponseEntity.ok(localeMessageService.getMessage(Operate.ADD_SUCCESS));
    }

    /**
     * 更新商品.
     */
    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody ProductUpdateReqBody reqBody) throws BizException {
        Product product = new Product();
        BeanUtils.copyProperties(reqBody, product);
        productService.update(product);

        return ResponseEntity.ok(localeMessageService.getMessage(Operate.UPDATE_SUCCESS));
    }

    /**
     * 删除商品.
     */
    @GetMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam Long productId) throws BizException {
        productService.delete(productId);

        return ResponseEntity.ok(localeMessageService.getMessage(Operate.UPDATE_SUCCESS));
    }

}

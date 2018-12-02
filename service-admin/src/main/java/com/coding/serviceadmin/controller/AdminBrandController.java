package com.coding.serviceadmin.controller;

import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.base.CommonStatus;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.DateUtils;
import com.coding.serviceadmin.request.brand.BrandAddReqBody;
import com.coding.serviceadmin.request.brand.BrandListReqBody;
import com.coding.serviceadmin.request.brand.BrandUpdateReqBody;
import com.coding.supermarket.domain.brand.service.BrandService;
import com.coding.supermarket.domain.product.model.Brand;
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
@RequestMapping(value = "/admin/brand")
public class AdminBrandController {

    @Inject
    private BrandService brandService;

    /**
     * 品牌列表.
     */
    @PostMapping(value = "/list")
    public ResponseEntity<Page<Brand>> list(@RequestBody BrandListReqBody reqBody) {
        Brand brand = new Brand();
        brand.setName(reqBody.getName());
        brand.setStatus(CommonStatus.IN_USE.getIndex());
        Pageable pageable = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        Page<Brand> page = brandService.list(brand, pageable);

        return ResponseEntity.ok(page);
    }

    /**
     * 新增品牌.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody BrandAddReqBody reqBody) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(reqBody, brand);
        brandService.add(brand);
        return ResponseEntity.ok("新增成功");
    }

    /**
     * 修改品牌信息.
     */
    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody BrandUpdateReqBody reqBody) throws BizException {
        Brand brand = new Brand();
        BeanUtils.copyProperties(reqBody, brand);
        brand.setUpdateTime(DateUtils.now());
        brandService.update(brand);
        return ResponseEntity.ok("修改成功");
    }

    /**
     * 删除品牌.
     */
    @GetMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) throws BizException {
        brandService.delete(id);
        return ResponseEntity.ok("删除成功");
    }
}

package com.github.caijh.supermarket.admin.controller;

import javax.inject.Inject;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.commons.util.DateUtils;
import com.github.caijh.framework.core.enums.CommonStatus;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.web.controller.BaseController;
import com.github.caijh.supermarket.admin.request.brand.BrandAddReqBody;
import com.github.caijh.supermarket.admin.request.brand.BrandListReqBody;
import com.github.caijh.supermarket.admin.request.brand.BrandUpdateReqBody;
import com.github.caijh.supermarket.product.model.Brand;
import com.github.caijh.supermarket.product.service.BrandService;
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
@RequestMapping(value = "/brand")
public class BrandController extends BaseController {

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
        Page<Brand> page = this.brandService.list(brand, pageable);

        return ResponseEntity.ok(page);
    }

    /**
     * 新增品牌.
     */
    @PostMapping(value = "/add")
    public void add(@RequestBody BrandAddReqBody reqBody) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(reqBody, brand);
        this.brandService.add(brand);
    }

    /**
     * 修改品牌信息.
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody BrandUpdateReqBody reqBody) throws BizException {
        Brand brand = new Brand();
        BeanUtils.copyProperties(reqBody, brand);
        brand.setUpdateTime(DateUtils.now());
        this.brandService.update(brand);
    }

    /**
     * 删除品牌.
     */
    @GetMapping(value = "/delete")
    public void delete(@RequestParam String id) throws BizException {
        this.brandService.delete(id);
    }

}

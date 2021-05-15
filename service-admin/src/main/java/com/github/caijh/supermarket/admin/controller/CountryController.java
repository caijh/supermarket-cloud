package com.github.caijh.supermarket.admin.controller;

import javax.inject.Inject;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.web.controller.BaseController;
import com.github.caijh.supermarket.admin.request.country.CountryAddReqBody;
import com.github.caijh.supermarket.admin.request.country.CountryListReqBody;
import com.github.caijh.supermarket.admin.request.country.CountryUpdateReqBody;
import com.github.caijh.supermarket.base.model.Country;
import com.github.caijh.supermarket.base.service.CountryService;
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
@RequestMapping(value = "/country")
public class CountryController extends BaseController {

    @Inject
    private CountryService countryService;

    /**
     * 国家列表.
     */
    @PostMapping(value = "/list")
    public Page<Country> list(@RequestBody CountryListReqBody reqBody) {
        Country country = new Country();
        BeanUtils.copyIgnoreNullProperties(reqBody, country);
        Pageable pageable = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        return this.countryService.list(country, pageable);
    }

    /**
     * 国家信息.
     *
     * @param id 国家id
     */
    @GetMapping(value = "/detail")
    public Country detail(@RequestParam("id") Long id) throws BizException {
        return this.countryService.detail(id);
    }

    /**
     * 增加国家.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody CountryAddReqBody reqBody) throws BizException {
        Country country = new Country();
        BeanUtils.copyProperties(reqBody, country);
        this.countryService.add(country);
        return ResponseEntity.ok("新增成功");
    }

    /**
     * 更新国家信息.
     */
    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody CountryUpdateReqBody reqBody) throws BizException {
        Country country = new Country();
        BeanUtils.copyIgnoreNullProperties(reqBody, country);

        this.countryService.update(country);

        return ResponseEntity.ok("修改成功");
    }

}

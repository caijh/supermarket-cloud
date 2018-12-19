package com.coding.supermarket.serviceadmin.controller;

import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.domain.country.model.Country;
import com.coding.commons.domain.country.service.CountryService;
import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.serviceadmin.request.country.CountryAddReqBody;
import com.coding.supermarket.serviceadmin.request.country.CountryListReqBody;
import com.coding.supermarket.serviceadmin.request.country.CountryUpdateReqBody;
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
@RequestMapping(value = "/admin/country")
public class AdminCountryController {

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
        return countryService.list(country, pageable);
    }

    /**
     * 国家信息.
     *
     * @param id 国家id
     */
    @GetMapping(value = "/detail")
    public Country detail(@RequestParam("id") Long id) throws BizException {
        return countryService.detail(id);
    }

    /**
     * 增加国家.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody CountryAddReqBody reqBody) throws BizException {
        Country country = new Country();
        BeanUtils.copyProperties(reqBody, country);
        countryService.add(country);
        return ResponseEntity.ok("新增成功");
    }

    /**
     * 更新国家信息.
     */
    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody CountryUpdateReqBody reqBody) throws BizException {
        Country country = new Country();
        BeanUtils.copyIgnoreNullProperties(reqBody, country);

        countryService.update(country);

        return ResponseEntity.ok("修改成功");
    }

}

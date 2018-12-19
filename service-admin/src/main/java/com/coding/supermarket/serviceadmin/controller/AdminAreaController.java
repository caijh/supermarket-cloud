package com.coding.supermarket.serviceadmin.controller;

import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.base.constant.Operate;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.domain.area.model.Area;
import com.coding.commons.domain.area.service.AreaService;
import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.serviceadmin.request.area.AreaAddReqBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/area")
public class AdminAreaController {

    @Inject
    private AreaService areaService;
    @Inject
    private LocaleMessageService localeMessageService;

    /**
     * 增加新的地区信息.
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody AreaAddReqBody reqBody) throws BizException {
        Area area = new Area();
        BeanUtils.copyIgnoreNullProperties(reqBody, area);
        areaService.add(area);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.ADD_SUCCESS));
    }
}

package com.github.caijh.supermarket.admin.controller;

import javax.inject.Inject;

import com.github.caijh.commons.util.BeanUtils;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.web.controller.BaseController;
import com.github.caijh.supermarket.admin.request.area.AreaAddReqBody;
import com.github.caijh.supermarket.base.model.Area;
import com.github.caijh.supermarket.base.service.AreaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/area")
public class AreaController extends BaseController {

    @Inject
    private AreaService areaService;

    /**
     * 增加新的地区信息.
     */
    @PostMapping(value = "/add")
    public void add(@RequestBody AreaAddReqBody reqBody) throws BizException {
        Area area = new Area();
        BeanUtils.copyIgnoreNullProperties(reqBody, area);
        this.areaService.add(area);
    }

}

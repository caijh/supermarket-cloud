package com.coding.serviceexpress.controller;

import javax.inject.Inject;

import com.coding.commons.util.AssertUtils;
import com.coding.supermarket.domain.express.model.Express;
import com.coding.supermarket.domain.express.model.ExpressRoute;
import com.coding.supermarket.domain.express.service.ExpressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/express")
public class ExpressController {

    @Inject
    private ExpressService expressService;

    @GetMapping(value = "/info")
    public ExpressRoute queryExpressRoute(@RequestParam String expressId, @RequestParam String expressNo) {
        Express express = expressService.get(expressId);
        AssertUtils.notNull(express);
        return expressService.queryExpressRoute(express, expressNo);
    }
}

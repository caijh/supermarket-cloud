package com.coding.supermarket.domain.express.service;

import javax.inject.Named;

import com.coding.supermarket.domain.express.model.ExpressClientSetting;
import com.coding.supermarket.domain.express.model.ExpressRoute;

@Named("kdniaoExpressClient")
public class KdniaoExpressClient implements ExpressClient {

    @Override
    public ExpressRoute queryExpressRoute(ExpressClientSetting settings, String expressNo) {

        return null;
    }

}

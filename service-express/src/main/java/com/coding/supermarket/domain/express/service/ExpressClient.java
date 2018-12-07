package com.coding.supermarket.domain.express.service;

import com.coding.supermarket.domain.express.model.ExpressClientSetting;
import com.coding.supermarket.domain.express.model.ExpressRoute;

public interface ExpressClient {

    ExpressRoute queryExpressRoute(ExpressClientSetting settings, String expressNo);

}

package com.coding.supermarket.domain.express.service;

import com.coding.supermarket.domain.express.model.ExpressClientSetting;
import com.coding.supermarket.domain.express.model.ExpressRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpressManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ExpressClient expressClient;

    public void setExpressClient(ExpressClient expressClient) {
        this.expressClient = expressClient;
    }

    public ExpressRoute queryExpressRoute(ExpressClientSetting expressClientSetting, String expressNo) {
        ExpressRoute expressRoute = null;
        try {
            expressRoute = expressClient.queryExpressRoute(expressClientSetting, expressNo);
        } catch (Exception e) {
            logger.error("queryExpressRoute fail", e);
        }
        return expressRoute;
    }
}

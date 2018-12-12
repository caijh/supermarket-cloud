package com.coding.supermarket.domain.express.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.util.CollectionUtils;
import com.coding.supermarket.domain.express.mapper.ExpressMapper;
import com.coding.supermarket.domain.express.model.Express;
import com.coding.supermarket.domain.express.model.ExpressClientSetting;
import com.coding.supermarket.domain.express.model.ExpressRoute;

@Named
public class ExpressServiceImpl implements ExpressService {

    @Inject
    private ExpressMapper expressMapper;

    @Inject
    private Map<String, ExpressClient> expressClientMap;

    @Override
    public Express get(String expressId) {
        return expressMapper.get(expressId);
    }

    @Override
    public ExpressRoute queryExpressRoute(Express express, String expressNo) {
        List<ExpressClientSetting> expressClientSettings = expressMapper.findExpressClientSetting(express.getId());
        if (CollectionUtils.isEmpty(expressClientSettings)) {
            return null;
        }
        Map<ExpressClient, ExpressClientSetting> supportedClients = new LinkedHashMap<>();
        expressClientSettings.forEach(e -> {
            ExpressClient expressClient = expressClientMap.get(e.getClient());
            if (expressClient != null) {
                supportedClients.put(expressClient, e);
            }
        });
        ExpressManager expressManager = new ExpressManager();
        ExpressRoute expressRoute = null;
        for (Map.Entry<ExpressClient, ExpressClientSetting> entry : supportedClients.entrySet()) {
            ExpressClientSetting expressClientSetting = entry.getValue();
            expressManager.setExpressClient(entry.getKey());
            expressRoute = expressManager.queryExpressRoute(expressClientSetting, expressNo);
            if (expressRoute != null) {
                break;
            }
        }
        if (expressRoute != null) {
            expressRoute.setExpressNo(expressNo);
            expressRoute.setExpressName(express.getName());
        }

        return expressRoute;
    }
}

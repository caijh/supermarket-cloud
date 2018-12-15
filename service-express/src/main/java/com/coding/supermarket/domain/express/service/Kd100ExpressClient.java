package com.coding.supermarket.domain.express.service;

import java.util.HashMap;
import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coding.commons.util.HttpClientUtils;
import com.coding.commons.util.MD5Utils;
import com.coding.commons.util.StringUtils;
import com.coding.supermarket.domain.express.exception.ExpressRouteException;
import com.coding.supermarket.domain.express.model.ClientConfig;
import com.coding.supermarket.domain.express.model.ExpressClientSetting;
import com.coding.supermarket.domain.express.model.ExpressRoute;
import com.coding.supermarket.domain.express.model.ExpressRouteItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("kd100ExpressClient")
public class Kd100ExpressClient implements ExpressClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ExpressRoute queryExpressRoute(ExpressClientSetting settings, String expressNo) {
        ExpressRoute expressRoute = ExpressRoute.noneExpressRoute();
        try {
            ClientConfig clientConfig = JSON.parseObject(settings.getClientConfig(), ClientConfig.class);
            String expressSupplierCode = JSON.parseObject(settings.getExpressConfig()).getString("expressSupplierCode");
            String param = new JSONObject().fluentPut("com", expressSupplierCode).fluentPut("num", expressNo).toJSONString();
            String customer = clientConfig.getAppCode();
            String key = clientConfig.getAppKey();
            String sign = MD5Utils.md5(param + key + customer);
            HashMap<String, String> params = new HashMap<>();
            params.put("param", param);
            params.put("sign", sign);
            params.put("customer", customer);
            String result = HttpClientUtils.post(clientConfig.getQueryUrl(), params);
            if (StringUtils.isBlank(result)) {
                throw new ExpressRouteException("no result");
            }
            extractResult(result, expressRoute);
        } catch (Exception e) {
            expressRoute = null;
            logger.error("queryExpressRoute fail", e);
        }
        return expressRoute;
    }

    private void extractResult(String result, ExpressRoute expressRoute) {
        JSONArray data = JSON.parseObject(result).getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject dataItemJson = data.getJSONObject(i);
            ExpressRouteItem expressRouteItem = new ExpressRouteItem();
            expressRouteItem.setDescription(dataItemJson.getString("context"));
            expressRouteItem.setOperateTime(dataItemJson.getString("ftime"));
            expressRoute.getItems().add(expressRouteItem);
        }
    }

}

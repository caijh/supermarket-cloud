package com.coding.supermarket.domain.express.service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coding.commons.util.DateUtils;
import com.coding.commons.util.HttpClientUtils;
import com.coding.commons.util.StringUtils;
import com.coding.supermarket.domain.express.exception.ExpressRouteException;
import com.coding.supermarket.domain.express.model.ClientConfig;
import com.coding.supermarket.domain.express.model.ExpressClientSetting;
import com.coding.supermarket.domain.express.model.ExpressRoute;
import com.coding.supermarket.domain.express.model.ExpressRouteItem;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named("cainiaoExpressClient")
public class CainiaoExpressClient implements ExpressClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ExpressRoute queryExpressRoute(ExpressClientSetting settings, String expressNo) {
        ExpressRoute expressRoute;
        try {
            expressRoute = new ExpressRoute();
            expressRoute.setItems(new ArrayList<>());
            ClientConfig clientConfig = JSON.parseObject(settings.getClientConfig(), ClientConfig.class);
            String expressSupplierCode = JSON.parseObject(settings.getExpressConfig()).getString("expressSupplierCode");
            Map<String, String> reqParams = prepareParams(expressNo, clientConfig, expressSupplierCode);
            String result = HttpClientUtils.post(clientConfig.getQueryUrl(), reqParams);

            if (StringUtils.isNotBlank(result)) {
                JSONObject resultJson = JSONObject.parseObject(result);
                if (StringUtils.isBlank(resultJson.getString("success")) || "false".equals(resultJson.getString("success"))) {
                    String errorMsg = StringUtils.isNotBlank(resultJson.getString("errorMsg")) ? resultJson.getString("errorMsg") : result;
                    throw new ExpressRouteException(errorMsg);
                }
                extractResult(resultJson, expressRoute);
            }
        } catch (Exception e) {
            expressRoute = null;
            logger.error("queryExpressRoute fail", e);
        }

        return expressRoute;
    }

    private static String getSign(String logisticsInterface, String keys, String charset) throws ExpressRouteException {
        try {
            String content = logisticsInterface + keys;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes(charset));
            return new String(Base64.encodeBase64(md.digest()), charset);
        } catch (Exception e) {
            throw new ExpressRouteException("验证签名失败");
        }
    }

    private void extractResult(JSONObject resultJson, ExpressRoute expressRoute) {
        JSONArray fullTraceDetail = resultJson.getJSONArray("fullTraceDetail");
        if (fullTraceDetail == null) {
            ExpressRouteItem item = new ExpressRouteItem();
            item.setDescription("暂无物流信息");
            item.setOperateTime(DateUtils.format(DateUtils.now()));
            expressRoute.getItems().add(item);
        } else {
            for (int i = 0; i < fullTraceDetail.size(); i++) {
                JSONObject routeItem = fullTraceDetail.getJSONObject(i);
                ExpressRouteItem item = new ExpressRouteItem();
                item.setDescription(routeItem.getString("desc"));
                item.setOperateTime(routeItem.getString("time"));
                expressRoute.getItems().add(item);
            }
        }
    }

    private Map<String, String> prepareParams(String expressNo, ClientConfig clientConfig, String expressSupplierCode) throws ExpressRouteException {
        String logisticsInterface = new JSONObject().fluentPut("appName", clientConfig.getAppName()).fluentPut("cpCode", expressSupplierCode).fluentPut("mailNo", expressNo).toJSONString();
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("logistics_interface", logisticsInterface);
        reqParams.put("logistic_provider_id", clientConfig.getAppCode());
        reqParams.put("msg_type", "LPC_PACK_PUB_QUERY");
        reqParams.put("data_digest", getSign(logisticsInterface, clientConfig.getAppKey(), "utf-8"));
        reqParams.put("to_code", "LD-PACKPUSH");
        return reqParams;
    }
}

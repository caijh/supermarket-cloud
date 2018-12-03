package com.coding.supermarket.domain.express.model;

import org.junit.Test;

public class SFExpressSupplierTest {

    @Test
    public void getExpressInfo() {
        SFRequest sfRequest = new SFRequest();
        sfRequest.setService("RouteService");
        sfRequest.setHead("HHDZSW_MyJPI");
        RouteRequestBody routeRequestBody = new RouteRequestBody();
        RouteRequest routeRequest = new RouteRequest("1", "1", "444000092338");
        routeRequestBody.setRouteRequest(routeRequest);
        sfRequest.setBody(routeRequestBody);
        SFExpressSupplier sfExpressSupplier = new SFExpressSupplier();
        String expressInfo = sfExpressSupplier.getExpressInfo(sfRequest);
        System.out.println(expressInfo);
    }
}

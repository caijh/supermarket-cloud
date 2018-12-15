package com.coding.supermarket.domain.express.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressRoute {

    private String expressName;

    private String expressNo;

    private List<ExpressRouteItem> items;

    public static ExpressRoute noneExpressRoute() {
        ExpressRoute expressRoute = new ExpressRoute();
        expressRoute.setItems(new ArrayList<>());
        return expressRoute;
    }

}

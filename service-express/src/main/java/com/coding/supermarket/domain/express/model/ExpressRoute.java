package com.coding.supermarket.domain.express.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressRoute {

    private String expressName;

    private String expressNo;

    private List<ExpressRouteItem> items;

}

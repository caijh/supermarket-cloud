package com.coding.supermarket.domain.express.service;

import com.coding.supermarket.domain.express.model.Express;
import com.coding.supermarket.domain.express.model.ExpressRoute;

public interface ExpressService {

    Express get(Long expressId);

    ExpressRoute queryExpressRoute(Express express, String expressNo);
}

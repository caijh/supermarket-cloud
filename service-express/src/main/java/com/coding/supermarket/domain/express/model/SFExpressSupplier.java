package com.coding.supermarket.domain.express.model;

import com.coding.commons.util.JaxbUtils;
import com.sf.csim.express.service.CallExpressServiceTools;

public class SFExpressSupplier implements ExpressSupplier {

    private final String url = "http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";

    @Override
    public String getName() {
        return "顺丰快递";
    }

    @Override
    public String getExpressInfo(Request request) {
        SFRequest sfRequest = (SFRequest) request;
        return CallExpressServiceTools
            .callSfExpressServiceByCSIM(url, JaxbUtils.toXml(sfRequest), sfRequest.getHead(),
                "Ev4melPycV3jl8OHiQoANjqWdJ2bcilG");
    }
}

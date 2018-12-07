package com.coding.supermarket.domain.express.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressRouteItem {
    /**
     * 描述
     */
    private String description;
    /**
     * 发生时间
     */
    private String operateTime;
    /**
     * 操作人
     */
    private String operatorName;

}

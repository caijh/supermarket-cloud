package com.coding.supermarket.domain.express.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressRouteItem {
    /**
     * 代码
     */
    private String state;
    /**
     * 描述
     */
    private String notes;
    /**
     * 发生时间
     */
    private String opTime;
    /**
     * 操作人
     */
    private String opercode;

}

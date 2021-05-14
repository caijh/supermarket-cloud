package com.coding.supermarket.domain.order.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.caijh.framework.core.model.PersistentObject;
import com.github.caijh.supermarket.base.model.Area;
import lombok.Data;
import org.hibernate.annotations.Type;

/**
 * 订单快递信息.
 */
@Data
@Entity
public class OrderDeliveryInfo implements PersistentObject<Long> {

    private static final long serialVersionUID = -8297443499609617394L;
    @Id
    private Long orderId;

    /**
     * 收件人.
     */
    private String consignee;

    /**
     * 收货人联系电话.
     */
    private String consignPhone;

    /**
     * 收件人身份证.
     */
    private String consignIdNo;

    /**
     * 收货人地址.
     */
    @Type(type = "jsonb")
    private List<Area> consignAddress;

    /**
     * 快递公司名称.
     */
    private String shippingCompName;

    /**
     * 快递单号.
     */
    private String shippingSn;

    /**
     * 发货时间.
     */
    private Date shippingTime;

    /**
     * 收到货时间.
     */
    private Date receiveTime;

    @JsonIgnore
    @Override
    public Long getId() {
        return this.orderId;
    }

}

package com.coding.supermarket.domain.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.supermarket.domain.order.enums.OrderStatusEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Order extends JpaBaseEntity<Long> {

    /**
     * 订单编号.
     */
    @Column(unique = true)
    private String oderSn;

    /**
     * 下单人.
     */
    private Long userId;

    /**
     * 订单金额.
     */
    private BigDecimal amount;

    /**
     * 运费.
     */
    private BigDecimal freightAmount;

    /**
     * 优惠金额.
     */
    private BigDecimal deductAmount;

    /**
     * 订单支付金额.
     */
    private BigDecimal payAmount;

    /**
     * 订单支付类型.
     */
    private Byte payType;

    /**
     * 订单产生的积分.
     */
    private Integer point;

    /**
     * 发票抬头.
     */
    private String invoiceTitle;

    /**
     * 订单状态.
     *
     * @see OrderStatusEnum
     */
    private Integer status;

    /**
     * 订单商品.
     */
    @Transient
    private List<OrderProductSku> skuList;

    private Date createTime;

    private Date updateTime;

}

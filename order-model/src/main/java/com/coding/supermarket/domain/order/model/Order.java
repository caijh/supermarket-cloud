package com.coding.supermarket.domain.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.github.caijh.supermarket.base.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = -4477144038963604999L;
    /**
     * 订单编号.
     */
    @Column(unique = true)
    private String orderNo;

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

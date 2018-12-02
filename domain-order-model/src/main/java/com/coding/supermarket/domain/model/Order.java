package com.coding.supermarket.domain.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
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
    private String no;

    private Long userId;

    /**
     * 订单金额.
     */
    private Integer amount;

    /**
     * 订单支付金额.
     */
    private Integer payAmount;

    /**
     * 订单商品.
     */
    @Transient
    private List<OrderProductSku> skuList;

    /**
     * 订单状态.
     *
     * @see OrderStatus
     */
    private Integer status;

    private Date createTime;
    private Date updateTime;
}

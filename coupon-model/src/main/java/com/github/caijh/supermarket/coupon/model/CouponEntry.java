package com.github.caijh.supermarket.coupon.model;

import java.util.Date;
import javax.persistence.Entity;

import com.github.caijh.supermarket.base.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CouponEntry extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = -1227842052876671979L;
    /**
     * 优惠获取方式.
     */
    private Integer userObtainType;

    /**
     * 优惠券id.
     */
    private Long couponId;

    private String couponImg;

    /**
     * 发行量.
     * 等于0, 表示不限量.
     */
    private Integer total;

    /**
     * 库存.
     */
    private Integer stock;

    private Date startTime;
    private Date endTime;

}

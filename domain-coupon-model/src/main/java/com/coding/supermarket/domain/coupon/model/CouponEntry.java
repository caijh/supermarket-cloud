package com.coding.supermarket.domain.coupon.model;

import java.util.Date;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.supermarket.domain.coupon.enums.CouponUserObtainTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CouponEntry extends JpaBaseEntity<Long> {
    /**
     * 优惠获取方式.
     *
     * @see CouponUserObtainTypeEnum
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

package com.coding.supermarket.domain.coupon.model;

import java.util.Date;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserCoupon extends JpaBaseEntity<Long> {
    private Long userId;

    private Long couponsId;

    /**
     * 用户优惠券状态.
     *
     * @see UserCouponStatus
     */
    private Integer status;

    private Date createTime;
    private Date updateTime;
}
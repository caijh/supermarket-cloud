package com.github.caijh.supermarket.coupon.model;

import java.util.Date;
import javax.persistence.Entity;

import com.github.caijh.supermarket.base.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCoupon extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = 7225499939749413089L;
    private Long userId;

    private Long couponsId;

    /**
     * 用户优惠券状态.
     */
    private Integer status;

    private Date createTime;
    private Date updateTime;

}

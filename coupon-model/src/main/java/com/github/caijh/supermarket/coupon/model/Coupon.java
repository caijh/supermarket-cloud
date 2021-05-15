package com.github.caijh.supermarket.coupon.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

import com.github.caijh.supermarket.base.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

/**
 * 优惠券.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Coupon extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = -6135940332030507394L;
    /**
     * 金额,单位分.
     */
    private Integer amount;

    /**
     * 使用优惠券要达到的金额.
     */
    private Integer amountUseLimit;

    /**
     * 优惠券使用的时间类型.
     */
    private Integer useTimeType;
    private Date startTime;
    private Date endTime;
    private Integer daysAfterReceive;

    /**
     * 优惠券适用的条件.
     */
    @Type(type = "jsonb")
    private List<CouponApplyRange> applyTo;

    /**
     * 是否可以叠加使用.
     */
    private Boolean superpositionUse;

    /**
     * 说明.
     */
    private String description;

    private Integer status;

    private Long createdBy;
    private Date createTime;
    private Long updatedBy;
    private Date updateTime;

}

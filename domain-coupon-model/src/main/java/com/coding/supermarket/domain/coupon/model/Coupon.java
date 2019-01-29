package com.coding.supermarket.domain.coupon.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.supermarket.domain.coupon.enums.CouponUseTimeTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * 优惠券.
 */
@Getter
@Setter
@Entity
public class Coupon extends JpaBaseEntity<Long> {

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
     *
     * @see CouponUseTimeTypeEnum
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

    /**
     * 状态.
     *
     * @see com.coding.commons.base.CommonStatus
     */
    private Integer status;

    private Long createdBy;
    private Date createTime;
    private Long updatedBy;
    private Date updateTime;
}

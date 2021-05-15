package com.github.caijh.supermarket.admin.request.coupons;

import java.util.Date;
import java.util.List;

import com.github.caijh.framework.core.model.ReqBody;
import com.github.caijh.supermarket.coupon.model.CouponApplyRange;
import lombok.Data;

@Data
public class CouponAddReqBody implements ReqBody {

    private Integer amount;
    private Integer amountUseLimit;
    private Integer useTimeType;
    private Date startTime;
    private Date endTime;
    private Integer daysAfterReceive;
    private List<CouponApplyRange> applyTo;
    private Boolean superpositionUse;
    private String description;
    private Long createdBy;

}

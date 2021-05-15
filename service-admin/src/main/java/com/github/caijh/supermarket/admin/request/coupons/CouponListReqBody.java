package com.github.caijh.supermarket.admin.request.coupons;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CouponListReqBody extends AbstractListReqBody {

    private Float amount;

}

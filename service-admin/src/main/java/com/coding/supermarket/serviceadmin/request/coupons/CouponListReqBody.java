package com.coding.supermarket.serviceadmin.request.coupons;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponListReqBody extends AbstractListReqBody {
    private Float amount;
}

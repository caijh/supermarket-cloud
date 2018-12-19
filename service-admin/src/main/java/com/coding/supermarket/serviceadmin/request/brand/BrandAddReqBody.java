package com.coding.supermarket.serviceadmin.request.brand;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandAddReqBody implements ReqBody {
    private String name;

    private String logo;

    private Long countryId;

    private Long createdBy;
}

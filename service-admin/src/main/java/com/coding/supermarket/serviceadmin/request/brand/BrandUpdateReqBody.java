package com.coding.supermarket.serviceadmin.request.brand;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandUpdateReqBody implements ReqBody {
    private Long id;
    private String name;
    private String logo;
    private Long countryId;
    private Long updatedBy;
}

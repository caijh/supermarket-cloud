package com.coding.serviceadmin.request.area;

import com.coding.commons.base.ReqBody;
import lombok.Data;

@Data
public class AreaAddReqBody implements ReqBody {

    private String code;

    private String name;

    private String parentCode;

    private Long countryId;
}

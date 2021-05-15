package com.github.caijh.supermarket.admin.request.area;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class AreaAddReqBody implements ReqBody {

    private String code;

    private String name;

    private String parentCode;

    private Long countryId;

}

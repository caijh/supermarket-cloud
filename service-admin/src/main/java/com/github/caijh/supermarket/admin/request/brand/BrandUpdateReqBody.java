package com.github.caijh.supermarket.admin.request.brand;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class BrandUpdateReqBody implements ReqBody {

    private Long id;
    private String name;
    private String logo;
    private Long countryId;
    private Long updatedBy;

}

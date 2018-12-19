package com.coding.supermarket.serviceadmin.request.role;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleAddReqBody implements ReqBody {
    private Long createdBy;

    private String name;
}

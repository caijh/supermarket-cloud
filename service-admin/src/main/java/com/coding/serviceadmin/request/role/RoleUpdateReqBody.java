package com.coding.serviceadmin.request.role;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateReqBody implements ReqBody {
    private Long id;

    private String name;

    private Long updatedBy;
}

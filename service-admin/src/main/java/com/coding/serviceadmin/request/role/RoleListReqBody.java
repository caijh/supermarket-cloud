package com.coding.serviceadmin.request.role;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleListReqBody extends AbstractListReqBody {
    private String code;

    private String name;
}

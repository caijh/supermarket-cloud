package com.coding.serviceadmin.request.role;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUserListReqBody extends AbstractListReqBody {
    private Long roleId;
}

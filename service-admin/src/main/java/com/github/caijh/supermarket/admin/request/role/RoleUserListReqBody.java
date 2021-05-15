package com.github.caijh.supermarket.admin.request.role;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleUserListReqBody extends AbstractListReqBody {

    private Long roleId;

}

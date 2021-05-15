package com.github.caijh.supermarket.admin.request.role;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleListReqBody extends AbstractListReqBody {

    private String code;

    private String name;

}

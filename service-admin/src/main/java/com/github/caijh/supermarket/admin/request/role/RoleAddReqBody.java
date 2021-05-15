package com.github.caijh.supermarket.admin.request.role;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleAddReqBody implements ReqBody {

    private Long createdBy;

    private String name;

}

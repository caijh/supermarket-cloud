package com.github.caijh.supermarket.admin.request.role;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class RoleUpdateReqBody implements ReqBody {

    private Long id;

    private String name;

    private Long updatedBy;

}

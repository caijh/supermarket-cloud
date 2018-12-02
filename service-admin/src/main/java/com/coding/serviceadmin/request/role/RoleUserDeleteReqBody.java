package com.coding.serviceadmin.request.role;

import java.util.List;

import com.coding.commons.base.ReqBody;
import lombok.Data;

@Data
public class RoleUserDeleteReqBody implements ReqBody {
    private Long roleId;

    private List<Long> userIds;
}

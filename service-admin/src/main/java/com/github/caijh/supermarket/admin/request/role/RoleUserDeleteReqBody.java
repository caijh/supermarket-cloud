package com.github.caijh.supermarket.admin.request.role;

import java.util.List;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class RoleUserDeleteReqBody implements ReqBody {

    private Long roleId;

    private List<Long> userIds;

}

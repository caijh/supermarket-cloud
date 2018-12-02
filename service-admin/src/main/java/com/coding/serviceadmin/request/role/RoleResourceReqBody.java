package com.coding.serviceadmin.request.role;

import java.util.List;

import lombok.Data;

@Data
public class RoleResourceReqBody {
    private Long roleId;

    private List<Long> resourceIds;
}

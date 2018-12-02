package com.coding.commons.domain.user.vo;

import com.coding.commons.domain.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleListVo extends User {
    private Boolean hasRole;
}

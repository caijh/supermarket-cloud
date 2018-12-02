package com.coding.serviceadmin.request.user;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListReqBody extends AbstractListReqBody {
    private String account;
}

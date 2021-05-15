package com.github.caijh.supermarket.admin.request.user;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListReqBody extends AbstractListReqBody {

    private String account;

}

package com.coding.gateway.request;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReqBody implements ReqBody {
    private String username;
    private String password;
}

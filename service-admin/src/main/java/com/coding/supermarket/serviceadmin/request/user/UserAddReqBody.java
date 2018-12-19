package com.coding.supermarket.serviceadmin.request.user;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddReqBody implements ReqBody {
    private String account;

    private String password;

    private String nickname;

    private String realName;

    private String headImg;

    private Integer age;

    private Integer status;

    private Long createdBy;
}

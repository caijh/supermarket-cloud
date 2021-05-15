package com.github.caijh.supermarket.admin.request.user;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
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

package com.coding.authorizationserver.request;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientAppListReqBody extends AbstractListReqBody {
    private String name;
}

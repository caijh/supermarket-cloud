package com.coding.supermarket.serviceadmin.request.resource;

import com.coding.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceListReqBody extends AbstractListReqBody {
    private String displayName;
}

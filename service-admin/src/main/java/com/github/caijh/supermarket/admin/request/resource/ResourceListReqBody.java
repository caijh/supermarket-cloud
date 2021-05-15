package com.github.caijh.supermarket.admin.request.resource;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceListReqBody extends AbstractListReqBody {

    private String displayName;

}

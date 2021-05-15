package com.github.caijh.supermarket.admin.request.brand;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BrandListReqBody extends AbstractListReqBody {

    private String name;

}

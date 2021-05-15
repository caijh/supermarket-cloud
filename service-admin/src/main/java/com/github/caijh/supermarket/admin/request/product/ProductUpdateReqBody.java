package com.github.caijh.supermarket.admin.request.product;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class ProductUpdateReqBody implements ReqBody {

    private String name;

    private String briefs;

}

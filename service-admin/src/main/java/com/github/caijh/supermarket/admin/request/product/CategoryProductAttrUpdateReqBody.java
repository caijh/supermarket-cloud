package com.github.caijh.supermarket.admin.request.product;

import java.util.List;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class CategoryProductAttrUpdateReqBody implements ReqBody {

    private Long id;

    private String name;

    private Integer type;

    private List<String> values;

}

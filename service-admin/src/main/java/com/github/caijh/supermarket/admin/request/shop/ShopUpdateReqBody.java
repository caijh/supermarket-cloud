package com.github.caijh.supermarket.admin.request.shop;

import java.util.List;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class ShopUpdateReqBody implements ReqBody {

    private Long id;

    private String name;

    private String address;

    private List<String> thumbnails;

    private String introduction;

    private Long updateBy;

}

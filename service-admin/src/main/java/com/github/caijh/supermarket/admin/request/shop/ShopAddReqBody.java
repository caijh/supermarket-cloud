package com.github.caijh.supermarket.admin.request.shop;

import java.util.List;

import com.github.caijh.framework.core.model.ReqBody;
import com.github.caijh.supermarket.base.model.Area;
import lombok.Data;

@Data
public class ShopAddReqBody implements ReqBody {

    private Long userId;

    private String name;

    private List<Area> address;

    private List<String> thumbnails;

    private String introduction;

    private Long createdBy;

}

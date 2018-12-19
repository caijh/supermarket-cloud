package com.coding.supermarket.serviceadmin.request.shop;

import java.util.List;

import com.coding.commons.base.ReqBody;
import com.coding.commons.domain.area.model.Area;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopAddReqBody implements ReqBody {

    private Long userId;

    private String name;

    private List<Area> address;

    private List<String> thumbnails;

    private String introduction;

    private Long createdBy;
}

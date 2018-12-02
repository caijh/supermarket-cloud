package com.coding.serviceadmin.request.shop;

import java.util.List;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopAddReqBody implements ReqBody {
    private String name;

    private String address;

    private List<String> thumbnails;

    private String introduction;

    private Long createdBy;
}

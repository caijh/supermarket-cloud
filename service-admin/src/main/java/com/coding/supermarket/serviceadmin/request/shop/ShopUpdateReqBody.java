package com.coding.supermarket.serviceadmin.request.shop;

import java.util.List;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopUpdateReqBody implements ReqBody {
    private Long id;

    private String name;

    private String address;

    private List<String> thumbnails;

    private String introduction;

    private Long updateBy;
}

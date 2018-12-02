package com.coding.serviceadmin.request.country;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryAddReqBody implements ReqBody {
    private Long id;

    private String name;

    private String enName;

    private String fullEnName;

    private String firstChar;

    private String abbreviation;
}

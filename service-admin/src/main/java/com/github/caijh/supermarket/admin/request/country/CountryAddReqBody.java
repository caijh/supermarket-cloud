package com.github.caijh.supermarket.admin.request.country;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class CountryAddReqBody implements ReqBody {

    private Long id;

    private String name;

    private String enName;

    private String fullEnName;

    private String firstChar;

    private String abbreviation;

}

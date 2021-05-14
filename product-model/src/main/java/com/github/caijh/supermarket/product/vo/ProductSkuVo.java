package com.github.caijh.supermarket.product.vo;

import java.util.List;

import com.github.caijh.framework.core.model.RespBody;
import com.github.caijh.supermarket.product.model.ProductOriginPlace;
import com.github.caijh.supermarket.product.model.ProductSkuAttr;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSkuVo implements RespBody {

    private static final long serialVersionUID = 6362156218854839518L;
    private Long id;
    private Long productId;
    private String thumbnail;
    private List<ProductSkuAttr> skuAttrs;
    private Integer price;
    private Integer referPrice;
    private ProductOriginPlace origin;
    private String barcode;
    private Integer status;

    private Integer stockNum;
    private Integer frozenNum;
    private Integer soldNum;

    private ProductVo productVo;

}

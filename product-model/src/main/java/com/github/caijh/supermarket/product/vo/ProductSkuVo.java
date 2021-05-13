package com.github.caijh.supermarket.product.vo;

import java.util.List;

import com.coding.commons.base.RespBody;
import com.coding.supermarket.domain.product.model.ProductOrigin;
import com.coding.supermarket.domain.product.model.ProductSkuAttr;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSkuVo implements RespBody {

    private Long id;
    private Long productId;
    private String thumbnail;
    private List<ProductSkuAttr> skuAttrs;
    private Integer price;
    private Integer referPrice;
    private ProductOrigin origin;
    private String barcode;
    private Integer status;

    private Integer stockNum;
    private Integer frozenNum;
    private Integer soldNum;

    private ProductVo productVo;

}

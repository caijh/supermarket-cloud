package com.coding.supermarket.domain.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coding.supermarket.domain.product.model.Brand;
import com.coding.supermarket.domain.product.model.Category;
import com.coding.supermarket.domain.product.model.ProductBaseAttr;
import com.coding.supermarket.domain.product.model.ProductOrigin;
import com.coding.supermarket.domain.product.model.ProductSku;
import com.coding.supermarket.domain.product.model.ProductSkuAttr;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVo implements Serializable {

    private Long id;

    private String name;

    private String briefs;

    private Category category;

    private Brand brand;

    private List<String> thumbnails;

    private String description;

    private Float taxRate;

    private ProductOrigin origin;

    private List<ProductBaseAttr> baseAttrs;

    private List<ProductSkuAttr> skuAttrs;

    private List<ProductSku> skuList;

    private Integer status;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

}

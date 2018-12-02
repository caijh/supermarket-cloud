package com.coding.supermarket.domain.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.coding.supermarket.domain.brand.model.Brand;
import com.coding.supermarket.domain.product.model.Category;
import com.coding.supermarket.domain.product.model.ProductSku;
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

    private Integer status;

    private List<ProductSku> skuList;

    private Long createdBy;
    private Date createTime;
    private Long updatedBy;
    private Date updateTime;
}

package com.coding.supermarket.domain.model;

import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.supermarket.domain.product.model.ProductSku;
import com.coding.supermarket.domain.product.vo.ProductVo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
public class OrderProductSku extends JpaBaseEntity<Long> {
    private Long orderId;

    @Type(type = "jsonb")
    private ProductVo product;

    @Type(type = "jsonb")
    private ProductSku productSku;

}

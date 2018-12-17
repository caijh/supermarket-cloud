package com.coding.supermarket.domain.product.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.coding.commons.base.PersistentObject;
import com.coding.commons.util.Base64Utils;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户收藏的商品.
 */
@Getter
@Setter
@Entity
public class ProductFavorite implements PersistentObject<String> {

    private Long userId;

    private Long productId;

    private Date createTime;

    @Id
    @Override
    public String getId() {
        return Base64Utils.encrypt((String.valueOf(userId) + String.valueOf(productId)).getBytes());
    }

}

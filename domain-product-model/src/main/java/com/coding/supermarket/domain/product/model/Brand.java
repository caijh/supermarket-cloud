package com.coding.supermarket.domain.product.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.coding.commons.base.PersistentObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌.
 */
@Getter
@Setter
@Entity
public class Brand implements PersistentObject<String> {

    @Id
    private String id;

    private String name;

    private String logo;

    /**
     * 状态.
     *
     * @see com.coding.commons.base.CommonStatus
     */
    private Integer status;

    /**
     * 品牌所属国家id.
     */
    private Long countryId;

    private Date createTime;

    private Long createdBy;

    private Date updateTime;

    private Long updatedBy;

    @Override
    public String getId() {
        return id;
    }

}

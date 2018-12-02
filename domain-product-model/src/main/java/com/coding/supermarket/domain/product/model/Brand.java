package com.coding.supermarket.domain.product.model;

import java.util.Date;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌.
 */
@Getter
@Setter
@Entity
public class Brand extends JpaBaseEntity<Long> {
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
}

package com.github.caijh.supermarket.product.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.github.caijh.framework.core.model.PersistentObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌.
 */
@Getter
@Setter
@Entity
public class Brand implements PersistentObject<String> {

    private static final long serialVersionUID = -6329765703855450553L;
    @Id
    private String id;

    private String name;

    private String logo;

    /**
     * 状态.
     * 0 - 在用; 1 - 删除.
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

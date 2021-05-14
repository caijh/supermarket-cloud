package com.github.caijh.supermarket.base.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;


/**
 * 门店
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Shop extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = -2274940952601547642L;

    private Long userId;

    private String name;

    @Type(type = "jsonb")
    private List<Area> address;

    @Type(type = "jsonb")
    private List<String> thumbnails;

    private String introduction;

    /**
     * 状态.
     */
    private Integer status;

    private Date createTime;
    private Long createdBy;
    private Date updateTime;
    private Long updatedBy;

}

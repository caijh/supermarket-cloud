package com.coding.commons.domain.shop.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.commons.domain.area.model.Area;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
public class Shop extends JpaBaseEntity<Long> {
    private String name;

    @Type(type = "jsonb")
    private List<Area> address;

    @Type(type = "jsonb")
    private List<String> thumbnails;

    private String introduction;

    /**
     * 状态.
     *
     * @see com.coding.commons.base.CommonStatus
     */
    private Integer status;

    private Date createTime;
    private Long createdBy;
    private Date updateTime;
    private Long updatedBy;
}

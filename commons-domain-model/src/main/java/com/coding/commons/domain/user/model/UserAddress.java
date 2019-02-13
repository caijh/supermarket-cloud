package com.coding.commons.domain.user.model;

import java.util.Date;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户地址.
 */
@Getter
@Setter
@Entity
public class UserAddress extends JpaBaseEntity<Long> {

    private Long userId;

    private String provinceId;

    private String cityId;

    private String districtId;

    private String zipCode;

    private String addr;

    private Boolean isDefault;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

}
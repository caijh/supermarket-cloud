package com.coding.commons.domain.user.model;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserAddress extends JpaBaseEntity<Long> {

    private Long id;

    private Long userId;

    private String provinceId;

    private String cityId;

    private String countyId;

    private String addr;

}

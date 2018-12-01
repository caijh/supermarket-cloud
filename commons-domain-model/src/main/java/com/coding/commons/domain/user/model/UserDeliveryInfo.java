package com.coding.commons.domain.user.model;

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
public class UserDeliveryInfo extends JpaBaseEntity<Long> {
    private Long userId;

    /**
     * 收件人.
     */
    private String consignee;

    /**
     * 收货人联系电话.
     */
    private String consignPhone;

    /**
     * 收件人身份证.
     */
    private String consignIdNo;

    /**
     * 收货人地址.
     */
    @Type(type = "jsonb")
    private List<Area> consignAddress;

    /**
     * 是否默认地址.
     */
    private Boolean isDefault;
}

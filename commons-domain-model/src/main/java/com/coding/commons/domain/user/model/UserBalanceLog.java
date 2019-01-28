package com.coding.commons.domain.user.model;

import java.math.BigDecimal;
import java.util.Date;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UserBalanceLog extends JpaBaseEntity<Long> {

    private Long userId;

    private BigDecimal amount;

    private Byte source;

    private String referNumber;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

}

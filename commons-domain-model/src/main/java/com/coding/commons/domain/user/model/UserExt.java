package com.coding.commons.domain.user.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.coding.commons.base.PersistentObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserExt implements PersistentObject<Long> {

    @Id
    private Long userId;

    private Integer grade;

    private Integer point;

    private BigDecimal balance;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

    @Override
    public Long getId() {
        return userId;
    }

}

package com.coding.commons.domain.user.model;

import java.math.BigDecimal;
import java.util.Date;

import com.coding.commons.base.PersistentObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserExt implements PersistentObject<Long> {

    @Id
    private Long userId;

    /**
     * 级别.
     */
    private Integer grade;

    /**
     * 积分.
     */
    private Integer point;

    /**
     * 余额.
     */
    private BigDecimal balance;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

    @JsonIgnore
    @Override
    public Long getId() {
        return userId;
    }

}

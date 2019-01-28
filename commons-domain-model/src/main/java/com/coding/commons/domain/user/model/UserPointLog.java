package com.coding.commons.domain.user.model;

import java.util.Date;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UserPointLog extends JpaBaseEntity<Long> {

    private Long userId;

    /**
     * 变更的积分数.
     */
    private Integer changePoint;

    /**
     * 来源.
     */
    private Byte source;

    /**
     * 积分来源的编号.
     */
    private String referNumber;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

}

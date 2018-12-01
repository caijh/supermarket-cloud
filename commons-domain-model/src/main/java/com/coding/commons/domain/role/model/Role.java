package com.coding.commons.domain.role.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends JpaBaseEntity<Long> {
    /**
     * 角色代号.
     */
    @Column(unique = true)
    private String code;

    private String name;

    /**
     * 是否系统内置角色,内置角色不能删除.
     */
    private Boolean isSysDefined;

    @JsonIgnore
    @Transient
    private List<Long> resourceIds;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;
}

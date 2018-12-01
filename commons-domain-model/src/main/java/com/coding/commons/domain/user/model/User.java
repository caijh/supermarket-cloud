package com.coding.commons.domain.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.commons.domain.role.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends JpaBaseEntity<Long> implements Serializable {
    @Column(unique = true)
    private String account;

    private String password;

    private String nickname;

    private String realName;

    private String headImg;

    private Date birthday;

    private Long createdBy;

    private Date createTime;

    private Long updatedBy;

    private Date updateTime;

    /**
     * 用户状态.
     *
     * @see UserStatusEnum
     */
    private Integer status;

    @JsonIgnore
    @Transient
    private List<Role> roles;
}

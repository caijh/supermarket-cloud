package com.coding.commons.domain.user.model;

import java.util.Date;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserLoginLog extends JpaBaseEntity<Long> {

    private Long userId;

    private String ip;

    private Date createTime;

}

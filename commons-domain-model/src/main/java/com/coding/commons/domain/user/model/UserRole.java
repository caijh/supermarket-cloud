package com.coding.commons.domain.user.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.coding.commons.base.PersistentObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRole implements PersistentObject<UserRoleId> {

    @EmbeddedId
    private UserRoleId userRoleId;

    @Column(insertable = false, updatable = false)
    private Long userId;

    @Column(insertable = false, updatable = false)
    private Long roleId;

    @Override
    public UserRoleId getId() {
        if (userRoleId == null) {
            userRoleId = new UserRoleId(userId, roleId);
        }
        return userRoleId;
    }
}

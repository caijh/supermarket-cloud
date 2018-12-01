package com.coding.commons.domain.role.model;

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
public class RoleResource implements PersistentObject<RoleResourceId> {

    @EmbeddedId
    private RoleResourceId roleResourceId;

    @Column(insertable = false, updatable = false)
    private Long roleId;

    @Column(insertable = false, updatable = false)
    private Long resourceId;

    @Override
    public RoleResourceId getId() {
        return roleResourceId;
    }
}

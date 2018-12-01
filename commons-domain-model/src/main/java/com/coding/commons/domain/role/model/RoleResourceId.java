package com.coding.commons.domain.role.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResourceId implements Serializable {
    private Long roleId;

    private Long resourceId;
}

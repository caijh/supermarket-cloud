package com.coding.commons.domain.role.repository;

import java.util.List;
import java.util.Set;

import com.coding.commons.domain.role.model.RoleResource;
import com.coding.commons.domain.role.model.RoleResourceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleResourceRepository extends JpaRepository<RoleResource, RoleResourceId> {
    List<RoleResource> findAllByRoleIdIn(Set<Long> roleIds);

    List<RoleResource> findAllByRoleId(Long roleId);
}

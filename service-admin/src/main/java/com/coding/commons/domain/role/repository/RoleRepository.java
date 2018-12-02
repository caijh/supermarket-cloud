package com.coding.commons.domain.role.repository;

import java.util.List;

import com.coding.commons.domain.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByIdIn(List<Long> roleIdList);
}

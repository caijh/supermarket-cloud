package com.coding.commons.domain.role.repository;

import com.coding.commons.domain.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

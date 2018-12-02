package com.coding.commons.domain.user.repository;

import java.util.List;

import com.coding.commons.domain.user.model.UserRole;
import com.coding.commons.domain.user.model.UserRoleId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    List<UserRole> findByRoleId(Long roleId, Pageable pageable);
}

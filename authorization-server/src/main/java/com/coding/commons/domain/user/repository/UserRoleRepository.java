package com.coding.commons.domain.user.repository;

import com.coding.commons.domain.user.model.UserRole;
import com.coding.commons.domain.user.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

}

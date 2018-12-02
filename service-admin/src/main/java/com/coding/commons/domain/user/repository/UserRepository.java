package com.coding.commons.domain.user.repository;

import com.coding.commons.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount(String username);
}

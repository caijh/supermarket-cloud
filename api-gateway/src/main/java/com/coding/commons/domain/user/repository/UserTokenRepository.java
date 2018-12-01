package com.coding.commons.domain.user.repository;

import java.util.Optional;

import com.coding.commons.domain.user.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {
    Optional<UserToken> findByAuthenticationId(String id);

    void deleteByAuthenticationId(String id);
}

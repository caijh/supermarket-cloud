package com.coding.commons.domain.client.model;

import java.util.Collection;

import com.coding.commons.base.IndexEnum;
import com.coding.commons.domain.user.model.User;
import com.coding.commons.domain.user.model.UserStatusEnum;
import com.coding.commons.util.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class ClientUserDetails implements UserDetails {
    private User user;

    public ClientUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(this.user.getRoles())) {
            return emptyList();
        }
        return this.user.getRoles().parallelStream().map(role -> (GrantedAuthority) role::getCode).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserStatusEnum.LOCKED != IndexEnum.valueOf(this.user.getStatus(), UserStatusEnum.class);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserStatusEnum.NORMAL == IndexEnum.valueOf(this.user.getStatus(), UserStatusEnum.class);
    }
}

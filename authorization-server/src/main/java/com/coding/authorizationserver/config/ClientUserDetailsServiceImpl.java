package com.coding.authorizationserver.config;

import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.domain.client.model.ClientUserDetails;
import com.coding.commons.domain.role.repository.RoleCacheRepository;
import com.coding.commons.domain.user.model.User;
import com.coding.commons.domain.user.model.UserRole;
import com.coding.commons.domain.user.repository.UserCacheRepository;
import com.coding.commons.domain.user.repository.UserRoleCacheRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * ClientUserDetailsServiceImpl.
 *
 * @author caijunhui
 **/
@Named(value = "clientUserDetailsService")
public class ClientUserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private UserCacheRepository userCacheRepository;

    @Inject
    private UserRoleCacheRepository userRoleCacheRepository;

    @Inject
    private RoleCacheRepository roleCacheRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userCacheRepository.findByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }

        Set<Long> roleIds = userRoleCacheRepository
            .findAll(Example.of(UserRole.builder().userId(user.getId()).build()))
            .parallelStream().map(UserRole::getRoleId)
            .collect(Collectors.toSet());
        user.setRoles(roleCacheRepository.findAllById(roleIds));

        return new ClientUserDetails(user);
    }
}

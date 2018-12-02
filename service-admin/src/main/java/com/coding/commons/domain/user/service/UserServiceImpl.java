package com.coding.commons.domain.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.resource.repository.ResourceCacheRepository;
import com.coding.commons.domain.role.repository.RoleResourceCacheRepository;
import com.coding.commons.domain.user.model.User;
import com.coding.commons.domain.user.model.UserRole;
import com.coding.commons.domain.user.model.UserStatusEnum;
import com.coding.commons.domain.user.repository.UserCacheRepository;
import com.coding.commons.domain.user.repository.UserRoleCacheRepository;
import com.coding.commons.util.AssertUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

@Cacheable(cacheNames = "users")
@Transactional
@Named
public class UserServiceImpl implements UserService {
    @Inject
    private UserCacheRepository userCacheRepository;

    @Inject
    private UserRoleCacheRepository userRoleCacheRepository;

    @Inject
    private RoleResourceCacheRepository roleResourceCacheRepository;

    @Inject
    private ResourceCacheRepository resourceCacheRepository;

    @Inject
    private LocaleMessageService localeMessageService;

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userCacheRepository;
    }

    @Override
    public List<Resource> listUserResources(Long userId) {
        List<Resource> resources = new ArrayList<>();

        UserRole userRole = UserRole.builder().userId(userId).build();
        Set<Long> roleIds = userRoleCacheRepository.findAll(Example.of(userRole)).parallelStream().map(UserRole::getRoleId).collect(Collectors.toSet());
        roleResourceCacheRepository.findAllByRoleIdIn(roleIds).parallelStream()
            .forEach(roleResource -> resourceCacheRepository.findById(roleResource.getResourceId()).ifPresent(resources::add));

        return resources;
    }

    @Transactional
    @Override
    public void lock(Long id) throws BizException {
        User user = userCacheRepository.findById(id).orElseThrow(() -> localeMessageService.getLocaleException("user.not_found"));
        user.setStatus(UserStatusEnum.LOCKED.getIndex());
        userCacheRepository.save(user);
    }

    @Override
    public void delete(Long id) throws BizException {
        User user = userCacheRepository.findById(id).orElseThrow(() -> localeMessageService.getLocaleException("user.not_found"));
        user.setStatus(UserStatusEnum.DELETED.getIndex());
        userCacheRepository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void add(User user) throws BizException {
        User dbUser = userCacheRepository.findByAccount(user.getAccount());
        AssertUtils.isTrue(dbUser == null, () -> localeMessageService.getLocaleException("user.exist"));
        userCacheRepository.save(user);
    }
}

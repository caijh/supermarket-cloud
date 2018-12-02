package com.coding.commons.domain.role.sevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.resource.model.ResourceHelper;
import com.coding.commons.domain.resource.repository.ResourceCacheRepository;
import com.coding.commons.domain.role.model.Role;
import com.coding.commons.domain.role.model.RoleResource;
import com.coding.commons.domain.role.model.RoleResourceId;
import com.coding.commons.domain.role.repository.RoleCacheRepository;
import com.coding.commons.domain.role.repository.RoleResourceCacheRepository;
import com.coding.commons.domain.user.model.User;
import com.coding.commons.domain.user.model.UserRole;
import com.coding.commons.domain.user.model.UserRoleId;
import com.coding.commons.domain.user.repository.UserCacheRepository;
import com.coding.commons.domain.user.repository.UserRoleCacheRepository;
import com.coding.commons.domain.user.vo.UserRoleListVo;
import com.coding.commons.util.AssertUtils;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.CollectionUtils;
import com.coding.commons.util.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private static final String ROLE_NOT_FOUND = "role.not_found";
    private static final String ROLE_CAN_NOT_DELETE = "role.can_not_delete";

    @Inject
    private RoleCacheRepository roleCacheRepository;
    @Inject
    private UserRoleCacheRepository userRoleCacheRepository;
    @Inject
    private RoleResourceCacheRepository roleResourceCacheRepository;
    @Inject
    private ResourceCacheRepository resourceCacheRepository;
    @Inject
    private UserCacheRepository userCacheRepository;
    @Inject
    private LocaleMessageService localeMessageService;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleCacheRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void delete(Long id) throws BizException {
        Role role =
            roleCacheRepository.findById(id).orElseThrow(() -> localeMessageService.getLocaleException(ROLE_NOT_FOUND));
        AssertUtils.isTrue(!role.getIsSysDefined(), () -> localeMessageService.getLocaleException(ROLE_CAN_NOT_DELETE));

        List<UserRole> userRoleList = new ArrayList<>(userRoleCacheRepository.findAll(Example.of(UserRole.builder().roleId(role.getId()).build())));
        userRoleCacheRepository.deleteAll(userRoleList);

        List<RoleResource> roleResourceList = new ArrayList<>(roleResourceCacheRepository.findAll(Example.of(RoleResource.builder().roleId(role.getId()).build())));
        roleResourceCacheRepository.deleteAll(roleResourceList);

        roleCacheRepository.delete(role);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void update(Role role) throws BizException {
        Role dbRole = roleCacheRepository.findById(role.getId()).orElseThrow(() -> localeMessageService.getLocaleException(ROLE_NOT_FOUND));
        BeanUtils.copyProperties(role, dbRole, null);

        roleCacheRepository.save(dbRole);
    }

    @Transactional
    @Override
    public void setResources(Long roleId, List<Long> resourceIds) throws BizException {
        Role role = roleCacheRepository.findById(roleId).orElseThrow(() -> localeMessageService.getLocaleException(ROLE_NOT_FOUND));

        List<RoleResource> roleResourceList = new ArrayList<>(roleResourceCacheRepository.findAll(Example.of(RoleResource.builder().roleId(role.getId()).build())));
        roleResourceCacheRepository.deleteAll(roleResourceList);

        role.setResourceIds(resourceIds);
        addRoleResource(role);
    }

    @Transactional
    @Override
    public Page<UserRoleListVo> listUser(Long roleId, Pageable pageable) {
        Page<User> userPage = userCacheRepository.findAll(Example.of(new User()), pageable);
        List<UserRoleId> userRoleIds =
            userPage.getContent().parallelStream().map(user -> new UserRoleId(user.getId(), roleId)).collect(Collectors.toList());
        Map<Long, Long> userRoleMap = userRoleCacheRepository.findAllById(userRoleIds).stream().collect(Collectors.toMap(UserRole::getUserId, UserRole::getRoleId));
        List<UserRoleListVo> users = userPage.getContent().parallelStream().map(user -> {
            UserRoleListVo userRoleListVo = new UserRoleListVo();
            BeanUtils.copyIgnoreNullProperties(user, userRoleListVo);
            userRoleListVo.setHasRole(userRoleMap.containsKey(roleId));
            return userRoleListVo;
        }).collect(Collectors.toList());
        return new PageImpl<>(users, userPage.getPageable(), userPage.getTotalElements());
    }

    @Transactional
    @Override
    public void addUsers(Long roleId, List<Long> userIds) throws BizException {
        Role role = roleCacheRepository.findById(roleId).orElseThrow(() -> localeMessageService.getLocaleException(ROLE_NOT_FOUND));
        List<UserRole> userRoles = userIds.parallelStream().map(userId -> UserRole.builder().roleId(role.getId()).userId(userId).build()).collect(Collectors.toList());
        userRoleCacheRepository.saveAll(userRoles);
    }

    @Transactional
    @Override
    public void deleteUsers(Long roleId, List<Long> userIds) {
        List<UserRole> userRoles = userIds.parallelStream().map(userId -> UserRole.builder().roleId(roleId).userId(userId).build()).collect(Collectors.toList());
        userRoleCacheRepository.deleteAll(userRoles);
    }

    @Override
    public List<Resource> listAllResources(Long roleId) throws BizException {
        Role role = roleCacheRepository.findById(roleId).orElseThrow(() -> localeMessageService.getLocaleException(ROLE_NOT_FOUND));
        Set<Long> resourceIds =
            roleResourceCacheRepository.findAllByRoleId(role.getId()).parallelStream().map(RoleResource::getResourceId).collect(Collectors.toSet());

        return resourceCacheRepository.findAllById(resourceIds);
    }

    @Override
    public List<Resource> preSetRoleResources(Long roleId) throws BizException {
        Role role = roleCacheRepository.findById(roleId).orElseThrow(() -> localeMessageService.getLocaleException(ROLE_NOT_FOUND));
        Set<Long> resourceIds =
            roleResourceCacheRepository.findAllByRoleId(role.getId()).parallelStream().map(RoleResource::getResourceId).collect(Collectors.toSet());
        List<Resource> allResources = resourceCacheRepository.findAll();
        allResources.forEach(resource -> resource.setSelected(resourceIds.contains(resource.getId())));
        return ResourceHelper.sortAsRootResources(allResources);
    }

    private void addRoleResource(Role role) {
        if (CollectionUtils.isNotEmpty(role.getResourceIds())) {
            role.getResourceIds().forEach(resourceId -> {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleResourceId(RoleResourceId.builder().resourceId(resourceId).roleId(role.getId()).build());
                roleResource.setRoleId(role.getId());
                roleResource.setResourceId(resourceId);
                roleResourceCacheRepository.save(roleResource);
            });
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void add(Role role) {
        roleCacheRepository.save(role);

        if (StringUtils.isBlank(role.getCode())) {
            role.setCode("ROLE_" + role.getId());
        }

        roleCacheRepository.save(role);
    }
}
